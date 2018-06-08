package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.parser.RequiredRuleParser;

/** 
 * @typename RequiredValidatorTest
 * @brief 比填写校验器测试
 * @author dly
 * @date 2018年6月6日 下午5:48:10
 * @version 1.0.0
 * @since
 * 
 */
public class RequiredValidatorTest {
    
    private Validator validator;
    private ValidatorContext context;
    private ValidateAttribute element;
    private boolean result;
    
    @Before
    public void setUp(){
        context = new ValidatorContext();
    }
    
    @Test
    public void testValidate() {
        element = new ValidateAttribute("name", "abcd", "");
        validator = new RequiredRuleParser().parse("required");
        result = validator.validate(context, element);
        assertTrue(result);
        
        element = new ValidateAttribute("name", null, "");
        validator = new RequiredRuleParser().parse("required");
        result = validator.validate(context, element);
        assertFalse(result);
    }

}
