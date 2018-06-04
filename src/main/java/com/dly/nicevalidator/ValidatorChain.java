package com.dly.nicevalidator;

import java.util.Iterator;
import java.util.List;

import com.dly.nicevalidator.domain.ValidatorElement;

public class ValidatorChain {

	private List<Validator> validators;
	
	private Iterator<Validator> iterator;
	
	public ValidatorChain(List<Validator> validators) {
		this.validators = validators;
		this.iterator = validators.iterator();
	}
	
	public void validate(ValidatorContext context, ValidatorElement element) {
		if(iterator.hasNext()) {
			iterator.next().validate(context, this, element);
		}
	}

	public List<Validator> getValidators() {
		return validators;
	}

}
