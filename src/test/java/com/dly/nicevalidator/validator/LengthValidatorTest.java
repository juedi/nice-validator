package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;
import com.dly.nicevalidator.domain.ValidatorElement;

/**
 * @typename LengthValidatorTest
 * @brief 长度校验器
 * @author dly
 * @date 2018年6月1日 下午1:38:38
 * @version 1.0.0
 * @since 1.0.0
 */
public class LengthValidatorTest {

	@Test
	public void testValidateGt() {
		Validator validator = new LengthValidator(5, LengthValidator.INFINITY);
		ValidatorContext context = doValidate(validator);
		
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请填写至少5个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test
	public void testValidateLt() {
		Validator validator = new LengthValidator(LengthValidator.INFINITY, 3);
		ValidatorContext context = doValidate(validator);
		
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请最多填写3个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test
	public void testValidateEq() {
		Validator validator = new LengthValidator(3, 3);
		ValidatorContext context = doValidate(validator);
		
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请填写3个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test
	public void testValidateBw() {
		Validator validator = new LengthValidator(1, 3);
		ValidatorContext context = doValidate(validator);
		
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请填写1到3个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidateWithException1() {
		Validator validator = new LengthValidator(-1, 0);
		doValidate(validator);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidateWithException2() {
		Validator validator = new LengthValidator(2, 1);
		doValidate(validator);
	}

	private ValidatorContext doValidate(Validator validator) {
		ValidatorContext context = new ValidatorContext();
		List<Validator> validators = new ArrayList<>();
		validators.add(validator);
		ValidatorChain chain = new ValidatorChain(validators);
		ValidatorElement element = new ValidatorElement("name", "abcd", chain);
		validator.validate(context, chain, element);
		return context;
	}

}
