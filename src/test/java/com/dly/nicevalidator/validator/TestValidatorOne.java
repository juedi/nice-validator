package com.dly.nicevalidator.validator;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;

/** 
 * @typename TestValidator
 * @brief 自定义的用户测试的校验器实现
 * @author dly
 * @date 2018年6月7日 上午10:42:45
 * @version 1.0.0
 * @since
 * 
 */
public class TestValidatorOne implements Validator{

    @Override
    public boolean validate(ValidatorContext context, ValidateAttribute element) {
        
        if((Integer)element.getValue() != 1){
            context.addError(new ErrorInfo(element.getAttributeName(), "值必须等于1"));
            return false;
        }
        return true;
    }

}
