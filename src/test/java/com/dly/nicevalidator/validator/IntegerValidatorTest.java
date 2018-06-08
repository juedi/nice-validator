package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.parser.IntegerRuleParser;

/** 
 * @typename IntegerValidatorTest
 * @brief 整形校验器测试
 * @author dly
 * @date 2018年6月6日 上午11:13:25
 * @version 1.0.0
 * @since
 * 
 */
public class IntegerValidatorTest {

    @Test
    public void testValidate() {
        ValidatorContext context = new ValidatorContext();
        ValidateAttribute element = new ValidateAttribute("age", 10, "");
        boolean result = new IntegerRuleParser().parse("integer").validate(context, element);
        assertTrue(result);
        assertTrue(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", "10", "");
        result = new IntegerRuleParser().parse("integer").validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        assertEquals(1, context.getResult().getErrors().size());
    }
    
    
    @Test(expected = ParseException.class)
    public void testValidateWithException() {
        ValidatorContext context = new ValidatorContext();
        ValidateAttribute element = new ValidateAttribute("age", 10, "");
        new IntegerRuleParser().parse("integer2").validate(context, element);
    }
    
    @Test
    public void testValidateGtZero(){
        ValidatorContext context = new ValidatorContext();
        ValidateAttribute element = new ValidateAttribute("age", 1, "");
        Validator validator = new IntegerRuleParser().parse("integer(+)");
        boolean result = validator.validate(context, element);
        assertTrue(result);
        assertTrue(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", 0, "");
        result = validator.validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        assertEquals(1, context.getResult().getErrors().size());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", -1, "");
        result = validator.validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        assertEquals(1, context.getResult().getErrors().size());
    }
    
    @Test
    public void testValidateGetZero(){
        ValidatorContext context = new ValidatorContext();
        ValidateAttribute element = new ValidateAttribute("age", 1, "");
        Validator validator = new IntegerRuleParser().parse("integer(+0)");
        boolean result = validator.validate(context, element);
        assertTrue(result);
        assertTrue(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", 0, "");
        result = validator.validate(context, element);
        assertTrue(result);
        assertTrue(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", -1, "");
        result = validator.validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        assertEquals(1, context.getResult().getErrors().size());
    }
    
    @Test
    public void testValidateLtZero(){
        ValidatorContext context = new ValidatorContext();
        ValidateAttribute element = new ValidateAttribute("age", -1, "");
        Validator validator = new IntegerRuleParser().parse("integer(-)");
        boolean result = validator.validate(context, element);
        assertTrue(result);
        assertTrue(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", 0, "");
        result = validator.validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", 1, "");
        result = validator.validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        assertEquals(1, context.getResult().getErrors().size());
    }
    
    @Test
    public void testValidateLetZero(){
        ValidatorContext context = new ValidatorContext();
        ValidateAttribute element = new ValidateAttribute("age", -1, "");
        Validator validator = new IntegerRuleParser().parse("integer(-0)");
        boolean result = validator.validate(context, element);
        assertTrue(result);
        assertTrue(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", 0, "");
        result = validator.validate(context, element);
        assertTrue(result);
        assertTrue(context.getResult().isSuccess());
        
        context = new ValidatorContext();
        element = new ValidateAttribute("age", 1, "");
        result = validator.validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        assertEquals(1, context.getResult().getErrors().size());
    }
}
