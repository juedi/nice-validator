package com.dly.nicevalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	
	private List<ValidatorElement<?>> validatorElements;
	
	private boolean failFast = true;
	
	private NiceValidator(List<ValidatorElement<?>> validatorElements) {
		this.validatorElements = validatorElements;
	}
	
	public static NiceValidator build(ValidatorElement<?> ...validatorElements) {
		List<ValidatorElement<?>> validatorList = new ArrayList<>();
		for(ValidatorElement<?> validatorElement : validatorElements) {
			validatorList.add(validatorElement);
		}
		return build(validatorList);
	}
	
	public static NiceValidator build(List<ValidatorElement<?>> validatorElements) {
		return new NiceValidator(validatorElements);
	}
	
	public NiceValidator failFast() {
		this.failFast = true;
		return this;
	}
	
	public NiceValidator failOver() {
		this.failFast = false;
		return this;
	}
	
	public NiceValidator addAttributeToContext(String attribure, Object value) {
		this.validatorContext.addAttribute(attribure, value);
		return this;
	}
	
	public NiceValidator addAttributesToContext(Map<String, Object> attributes) {
		this.validatorContext.addAttributes(attributes);
		return this;
	}
	
	public ValidationResult doValidator() {
		for(ValidatorElement<?> validatorElement : validatorElements) {
			
			validatorElement.validate(validatorContext);
			
			if(!validatorContext.getResult().isSuccess() && failFast) {
				return validatorContext.getResult();
			}
		}
		return validatorContext.getResult();
	}
}
