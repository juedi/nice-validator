package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.parser.CheckedRuleParser;

/** 
 * @typename CheckedValidatorTest
 * @brief checked校验器测试
 * @author dly
 * @date 2018年6月7日 下午4:39:57
 * @version 1.0.0
 * @since
 * 
 */
public class CheckedValidatorTest {

    private Validator validator;
    private ValidatorContext context;
    private ValidateAttribute element;
    private boolean result;
    
    @Before
    public void setUp(){
        context = new ValidatorContext();
        element = new ValidateAttribute("name", new String[]{"a", "b", "c"}, "");
    }
    
    @Test
    public void testValidate() {
        validator = new CheckedRuleParser().parse("checked");
        result = validator.validate(context, element);
        assertTrue(result);
        
        validator = new CheckedRuleParser().parse("checked(2)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        validator = new CheckedRuleParser().parse("checked(4)");
        result = validator.validate(context, element);
        assertFalse(result);
        
        validator = new CheckedRuleParser().parse("checked(2~)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        validator = new CheckedRuleParser().parse("checked(4~)");
        result = validator.validate(context, element);
        assertFalse(result);
        
        validator = new CheckedRuleParser().parse("checked(~4)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        validator = new CheckedRuleParser().parse("checked(~2)");
        result = validator.validate(context, element);
        assertFalse(result);
        
        validator = new CheckedRuleParser().parse("checked(2~4)");
        result = validator.validate(context, element);
        assertTrue(result);
    }

}
