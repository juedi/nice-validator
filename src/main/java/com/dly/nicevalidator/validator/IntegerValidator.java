package com.dly.nicevalidator.validator;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;
import com.dly.nicevalidator.enums.IntegerRuleEnum;

/**
 * @typename: IntegerValidator
 * @brief: 整形数据校验器
 * @author: dly
 * @date: 2018年6月5日 上午11:30:55
 * @version: 1.0.0
 * @since
 * 
 */
public class IntegerValidator implements Validator {

    public IntegerRuleEnum integerRule;

    /**
     * 创建一个新的实例 IntegerValidator.
     * 
     * @param integerRule
     *            校验规则
     */
    public IntegerValidator(IntegerRuleEnum integerRule) {
        this.integerRule = integerRule;
    }

    @Override
    public boolean validate(ValidatorContext context, ValidateAttribute element) {
        // 空值默认通过
        if (element.getValue() == null) {
            return true;
        }
        // 不是整形，不通过
        if (!(element.getValue() instanceof Integer)) {
            context.addError(new ErrorInfo(element.getAttributeName(), "请填写整数"));
            return false;
        }

        Integer value = (Integer) element.getValue();

        if (IntegerRuleEnum.INTEGER_POSITIVE.equals(integerRule) && value <= 0) {
            context.addError(new ErrorInfo(element.getAttributeName(), "请填写正整数"));
            return false;
        } 
        
        else if (IntegerRuleEnum.INTEGER_POSITIVE_AND_ZERO.equals(integerRule) && value < 0) {
            context.addError(new ErrorInfo(element.getAttributeName(), "请填写正整数和0"));
            return false;
        } 
        
        else if (IntegerRuleEnum.INTEGER_NEGATIVE.equals(integerRule) && value >= 0) {
            context.addError(new ErrorInfo(element.getAttributeName(), "请填写负整数"));
            return false;
        } 
        
        else if (IntegerRuleEnum.INTEGER_NEGATIVE_AND_ZERO.equals(integerRule) && value > 0) {
            context.addError(new ErrorInfo(element.getAttributeName(), "请填写负整数和0"));
            return false;
        }

        return true;
    }

}
