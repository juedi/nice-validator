package com.dly.nicevalidator.domain;

import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorContext;

/**
 * @typename ValidatorElement
 * @brief 待验证的属性信息
 * @author dly
 * @date 2018年5月31日 下午3:10:32
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidatorElement {

	/**属性名*/
	private String attributeName;
	
	/**属性值*/
	private Object value;
	
	/**校验器链，每个属性会由多个校验器进行校验*/
	private ValidatorChain chain;
	

	public ValidatorElement(String attributeName, Object value, ValidatorChain chain) {
		this.attributeName = attributeName;
		this.value = value;
		this.chain = chain;
	}

	public void validate(ValidatorContext context) {
		chain.validate(context, this);
	}
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public ValidatorChain getChain() {
		return chain;
	}

	public void setChain(ValidatorChain chain) {
		this.chain = chain;
	}
	
}
