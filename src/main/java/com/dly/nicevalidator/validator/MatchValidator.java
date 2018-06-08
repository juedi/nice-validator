package com.dly.nicevalidator.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;
import com.dly.nicevalidator.enums.MatchRuleEnum;

/** 
 * @typename: MatchValidator
 * @brief: 和其它字段匹配的校验器，比如密码二次填写验证，开始时间和结束时间的比较验证
 * @author: dly
 * @date: 2018年6月5日 上午11:30:30
 * @version: 1.0.0
 * @since
 * 
 */
public class MatchValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchValidator.class);
    
    private String other;
    private String operator;

    /**
     * 创建一个新的实例 MatchValidator.
     * @param operator 操作符
     * @param other 其它属性的属性名
     */
    public MatchValidator(String operator, String other) {
        this.operator = operator;
        this.other = other;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public boolean validate(ValidatorContext context, ValidateAttribute element) {
        //空值不做处理，直接返回
        if(element.getValue() == null){
            return true;
        }
        
        Object attribute = context.getAttribute(other);
        if(!(attribute instanceof ValidateAttribute)){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("比较字段%s不存在", other)));
            return false;
        }
        ValidateAttribute otherAttribute = (ValidateAttribute) attribute;
        if(otherAttribute.getValue() == null){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("比较字段%s没有值", other)));
            return false;
        }
        
        if(!(element.getValue() instanceof Comparable)
                || !(otherAttribute.getValue() instanceof Comparable)
                || element.getValue().getClass() != otherAttribute.getValue().getClass()){
            LOGGER.error("数据类型{}和{}无法比较", element.getValue().getClass(), otherAttribute.getValue().getClass());
            context.addError(new ErrorInfo(element.getAttributeName(), "数据类型无法比较"));
            return false;
        }
            
        Comparable otherValue = (Comparable) otherAttribute.getValue();
        Comparable value = (Comparable) element.getValue();
        
        if(MatchRuleEnum.EQ.is(operator) && value.compareTo(otherValue) != 0){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("当前字段值必须和%s字段的值匹配", other)));
            return false;
        }
        
        if(MatchRuleEnum.NEQ.is(operator) && value.compareTo(otherValue) == 0){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("当前字段值必须和%s字段值不同", other)));
            return false;
        }
        
        if(MatchRuleEnum.GT.is(operator) && value.compareTo(otherValue) <= 0){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("当前字段值必须大于%s字段值", other)));
            return false;
        }
        
        if(MatchRuleEnum.GTE.is(operator) && value.compareTo(otherValue) < 0){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("当前字段值必须大于等于%s字段值", other)));
            return false;
        }
        
        if(MatchRuleEnum.LT.is(operator) && value.compareTo(otherValue) >= 0){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("当前字段值必须小于%s字段值", other)));
            return false;
        }
        
        if(MatchRuleEnum.LTE.is(operator) && value.compareTo(otherValue) > 0){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("当前字段值必须小于等于%s字段值", other)));
            return false;
        }
        
        return true;
    }

}
