package com.dly.nicevalidator.validator;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;

/**
 * @typename RequiredValidator
 * @brief required 校验器，校验必填项 
 * @author dly
 * @date 2018年5月31日 下午3:51:48
 * @version 1.0.0
 * @since 1.0.0
 */
public class RequiredValidator implements Validator{

	@Override
	public boolean validate(ValidatorContext context, ValidateAttribute element) {
		if(element.getValue() == null) {
			context.addError(new ErrorInfo(element.getAttributeName(), "不可为空"));
			return false;
		}
		return true;
	}

}
