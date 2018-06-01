package com.dly.nicevalidator;

/**
 * @typename RequiredValidator
 * @brief TODO 
 * @author dly
 * @date 2018年5月31日 下午3:51:48
 * @version 1.0.0
 * @since 1.0.0
 */
public class RequiredValidator implements Validator {

	@Override
	public boolean validate(ValidatorContext context, ValidatorChain chain, ValidatorElement element) {
		if(element.getValue() == null) {
			context.addError(element.getAttribute() + "不可为空");
			return false;
		}
		else {
			chain.validate(context, element);
			return true;
		}
	}

}
