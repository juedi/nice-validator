package com.dly.nicevalidator.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.LengthValidator;

/**
 * @typename LengthRuleParserTest
 * @brief 长度校验规则测试类
 * @author dly
 * @date 2018年6月1日 上午11:15:43
 * @version 1.0.0
 * @since 1.0.0
 */
public class LengthRuleParserTest {

	@Test
	public void testEq() throws Exception {
		Validator validator = new LengthRuleParser().parse("length(1)");
		assertTrue(validator instanceof LengthValidator);
		LengthValidator v = (LengthValidator)validator;
		assertEquals(1, getFieldValue(v, "min"));
		assertEquals(1, getFieldValue(v, "max"));
	}
	
	@Test
	public void testGt() throws Exception {
		Validator validator = new LengthRuleParser().parse("length(1~)");
		assertTrue(validator instanceof LengthValidator);
		LengthValidator v = (LengthValidator)validator;
		assertEquals(1, getFieldValue(v, "min"));
		assertEquals(-1, getFieldValue(v, "max"));
	}
	
	@Test
	public void testLt() throws Exception {
		Validator validator = new LengthRuleParser().parse("length(~1)");
		assertTrue(validator instanceof LengthValidator);
		LengthValidator v = (LengthValidator)validator;
		assertEquals(-1, getFieldValue(v, "min"));
		assertEquals(1, getFieldValue(v, "max"));
	}
	
	@Test
	public void testBw() throws Exception {
		Validator validator = new LengthRuleParser().parse("length(1~2)");
		assertTrue(validator instanceof LengthValidator);
		LengthValidator v = (LengthValidator)validator;
		assertEquals(1, getFieldValue(v, "min"));
		assertEquals(2, getFieldValue(v, "max"));
	}
	
	@Test(expected=ParseException.class)
	public void testWithException() throws Exception {
		Validator validator = new LengthRuleParser().parse("length(1~ddd)");
		assertTrue(validator instanceof LengthValidator);
	}
	
	private Object getFieldValue(Object obj, String fieldName) {
		Field field;
		try {
			field = obj.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			Object value = field.get(obj);
			field.setAccessible(false);
			return value;
		} catch (Exception e) {
			return null;
		}
	}

}
