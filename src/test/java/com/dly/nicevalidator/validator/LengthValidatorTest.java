package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.parser.LengthRuleParser;

/**
 * @typename LengthValidatorTest
 * @brief 长度校验器
 * @author dly
 * @date 2018年6月1日 下午1:38:38
 * @version 1.0.0
 * @since 1.0.0
 */
public class LengthValidatorTest {
    
    private Validator validator;
    private ValidatorContext context;
    private ValidateAttribute element;
    private boolean result;
    
    @Before
    public void setUp(){
        context = new ValidatorContext();
        element = new ValidateAttribute("name", "abcd", "");
    }

	@Test
	public void testValidateGt() {
	    validator = new LengthRuleParser().parse("length(3~)");
	    result = validator.validate(context, element);
	    assertTrue(result);
	    
	    validator = new LengthRuleParser().parse("length(5~)");
        result = validator.validate(context, element);
        assertFalse(result);
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请填写至少5个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test
	public void testValidateLt() {
	    validator = new LengthRuleParser().parse("length(~4)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        validator = new LengthRuleParser().parse("length(~3)");
        result = validator.validate(context, element);
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请最多填写3个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test
	public void testValidateEq() {
	    validator = new LengthRuleParser().parse("length(4)");
        result = validator.validate(context, element);
        assertTrue(result);
	    
        validator = new LengthRuleParser().parse("length(3)");
        result = validator.validate(context, element);
        assertFalse(result);
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请填写3个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test
	public void testValidateBw() {
	    validator = new LengthRuleParser().parse("length(1~4)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        validator = new LengthRuleParser().parse("length(1~3)");
        result = validator.validate(context, element);
        assertFalse(result);
		assertFalse(context.getResult().isSuccess());
		assertEquals(new ErrorInfo("name", "请填写1到3个字符").toString(), context.getResult().getErrors().get(0).toString());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testValidateWithException1() {
	    new LengthRuleParser().parse("length(5~4)");
	}
	
	@Test(expected=ParseException.class)
    public void testValidateWithException2() {
        new LengthRuleParser().parse("length(~)");
    }
}
