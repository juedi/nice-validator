package com.dly.nicevalidator;

/**
 * @typename LengthValidator
 * @brief TODO 
 * @author dly
 * @date 2018年5月31日 下午3:53:31
 * @version 1.0.0
 * @since 1.0.0
 */
public class LengthValidator implements Validator{

	@Override
	public boolean validate(ValidatorContext context, ValidatorChain chain, ValidatorElement element) {
		if(element.getValue().toString().length() > 3) {
			context.addError(element.getAttribute() + "长度不能超过3");
			return false;
		}
		chain.validate(context, element);
		return true;
	}

}
