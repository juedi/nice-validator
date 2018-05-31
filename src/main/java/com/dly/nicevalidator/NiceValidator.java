package com.dly.nicevalidator;

import java.util.ArrayList;
import java.util.List;

/**
 * @typename NiceValidator
 * @brief TODO 
 * @author dly
 * @date 2018年5月31日 下午2:35:38
 * @version 1.0.0
 * @since 1.0.0
 */
public class NiceValidator {
	
	private ValidatorContext validatorContext = new ValidatorContext();
	
	private List<ValidatorElement> validatorElements;
	
	private boolean failFast = true;
	
	private NiceValidator(List<ValidatorElement> validatorElements) {
		this.validatorElements = validatorElements;
	}
	
	public static NiceValidator build(ValidatorElement ...ValidatorElement) {
		List<ValidatorElement> validatorList = new ArrayList<>();
		for(ValidatorElement validatorElement : ValidatorElement) {
			validatorList.add(validatorElement);
		}
		return new NiceValidator(validatorList);
	}
	
	public NiceValidator failFast(boolean failFast) {
		this.failFast = failFast;
		return this;
	}
	
	
	public ValidationResult doValidator() {
		for(ValidatorElement validatorElement : validatorElements) {
			
			validatorElement.validate(validatorContext);
			
			if(!validatorContext.getResult().isSuccess() && failFast) {
				return validatorContext.getResult();
			}
		}
		return validatorContext.getResult();
	}
}
