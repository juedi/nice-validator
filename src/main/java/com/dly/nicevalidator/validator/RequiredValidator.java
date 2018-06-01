package com.dly.nicevalidator.validator;

import com.dly.nicevalidator.ErrorMsg;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.ValidatorElement;

/**
 * @typename RequiredValidator
 * @brief TODO 
 * @author dly
 * @date 2018年5月31日 下午3:51:48
 * @version 1.0.0
 * @since 1.0.0
 */
public class RequiredValidator implements Validator<Object> {

	@Override
	public boolean validate(ValidatorContext context, ValidatorChain chain, ValidatorElement<Object> element) {
		if(element.getValue() == null) {
			context.addError(new ErrorMsg(element.getAttribute(), "不可为空"));
			return false;
		}
		else {
			chain.validate(context, element);
			return true;
		}
	}

}
