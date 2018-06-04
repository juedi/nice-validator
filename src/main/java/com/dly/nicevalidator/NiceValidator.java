package com.dly.nicevalidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dly.nicevalidator.domain.ValidateAttribute;
import com.dly.nicevalidator.domain.ValidateResult;
import com.dly.nicevalidator.domain.ValidatorElement;

/**
 * @typename NiceValidator
 * @brief 校验过程构建类，是校验的入口，通过该类构建校验的配置，做校验
 * @author dly
 * @date 2018年5月31日 下午2:35:38
 * @version 1.0.0
 * @since 1.0.0
 */
public final class NiceValidator {

	private ValidatorContext validatorContext = new ValidatorContext();

	private List<ValidatorElement> validatorElements;

	private boolean failFast = true;

	private NiceValidator(List<ValidatorElement> validatorElements) {
		this.validatorElements = validatorElements;
		//将校验的属性都放入到
		for(ValidatorElement validatorElement : validatorElements) {
			this.addAttributeToContext(validatorElement.getAttributeName(), validatorElement.getValue());
		}
	}

	public static NiceValidator build(ValidatorElement... validatorElements) {
		List<ValidatorElement> validatorList = new ArrayList<>();
		for (ValidatorElement validatorElement : validatorElements) {
			validatorList.add(validatorElement);
		}
		return build(validatorList);
	}

	public static NiceValidator build(List<ValidatorElement> validatorElements) {
		return new NiceValidator(validatorElements);
	}

	public static NiceValidator buildFromConvert(List<ConvertToValidateAttributeAble> convertAbles) {
		List<ValidatorElement> elements = new ArrayList<>();
		for (ConvertToValidateAttributeAble convertAble : convertAbles) {
			ValidateAttribute attr = convertAble.convertToValidateAttribute();
			List<Validator> validators = ValidatorFactory.createValidators(attr.getValidateExpression());
			ValidatorChain chain = new ValidatorChain(validators);
			ValidatorElement element = new ValidatorElement(attr.getAttributeName(), attr.getValue(), chain);
			elements.add(element);
		}
		return build(elements);
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

	public ValidateResult doValidator() {
		for (ValidatorElement validatorElement : validatorElements) {

			validatorElement.validate(validatorContext);

			if (!validatorContext.getResult().isSuccess() && failFast) {
				return validatorContext.getResult();
			}
		}
		return validatorContext.getResult();
	}
}
