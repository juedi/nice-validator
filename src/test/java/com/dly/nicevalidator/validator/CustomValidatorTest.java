package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.parser.CustomRuleParser;

/** 
 * @typename CustomValidatorTest
 * @brief 自定义校验器测试
 * @author dly
 * @date 2018年6月7日 上午10:41:16
 * @version 1.0.0
 * @since
 * 
 */
public class CustomValidatorTest {
    
    private ValidatorContext context;
    private ValidateAttribute element;
    
    @Before
    public void setUp(){
        context = new ValidatorContext();
    }
    @Test
    public void testValidate() {
        element = new ValidateAttribute("rete", 1, "");
        Validator validator = new CustomRuleParser().parse("custom(com.dly.nicevalidator.validator.TestValidatorOne)");
        boolean result = validator.validate(context, element);
        assertTrue(result);
        
        
        element = new ValidateAttribute("rete", 2, "");
        validator = new CustomRuleParser().parse("custom(com.dly.nicevalidator.validator.TestValidatorOne)");
        result = validator.validate(context, element);
        assertFalse(result);
        assertFalse(context.getResult().isSuccess());
        assertEquals("值必须等于1", context.getResult().getErrors().get(0).getError());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testValidateWithException() {
        element = new ValidateAttribute("rete", 1, "");
        new CustomRuleParser().parse("custom(com.dly.nicevalidator.validator.TestValidatorTwo)");
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testValidateWithExceptionNoClass() {
        element = new ValidateAttribute("rete", 1, "");
        new CustomRuleParser().parse("custom(com.dly.nicevalidator.validator.TestValidatorddd)");
    }

}
