package com.dly.nicevalidator.validator;

import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ValidatorElement;

/**
 * @typename CheckedValidator
 * @brief 单选、多选情况下使用 
 * @author dly
 * @date 2018年6月4日 下午6:40:15
 * @version 1.0.0
 * @since 1.0.0
 */
public class CheckedValidator implements Validator {

	@Override
	public boolean validate(ValidatorContext context, ValidatorChain chain, ValidatorElement element) {
		
		return false;
	}

}
