package com.dly.nicevalidator;

/**
 * @typename ValidatorElement
 * @brief 待验证的属性信息
 * @author dly
 * @date 2018年5月31日 下午3:10:32
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidatorElement<T> {

	/**属性名*/
	private String attribute;
	
	/**属性值*/
	private T value;
	
	/**校验器链，每个属性会由多个校验器进行校验*/
	private ValidatorChain chain;
	

	public ValidatorElement(String attribute, T value, ValidatorChain chain) {
		this.attribute = attribute;
		this.value = value;
		this.chain = chain;
	}

	public void validate(ValidatorContext context) {
		chain.validate(context, this);
	}
	
	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ValidatorChain getChain() {
		return chain;
	}

	public void setChain(ValidatorChain chain) {
		this.chain = chain;
	}
	
}
