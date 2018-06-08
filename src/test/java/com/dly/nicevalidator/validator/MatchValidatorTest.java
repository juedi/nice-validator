package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.parser.MatchRuleParser;

/** 
 * @typename MatchValidatorTest
 * @brief 比较校验器
 * @author dly
 * @date 2018年6月7日 下午4:24:03
 * @version 1.0.0
 * @since
 * 
 */
public class MatchValidatorTest {

    private Validator validator;
    private ValidatorContext context;
    private ValidateAttribute element;
    private boolean result;
    
    @Before
    public void setUp(){
        context = new ValidatorContext();
        element = new ValidateAttribute("name", "eric", "");
    }
    
    @Test
    public void testValidateEq() {
        ValidateAttribute attribute = new ValidateAttribute("first", "eric", "");
        context.addAttribute("first", attribute);
        
        validator = new MatchRuleParser().parse("match(first)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        validator = new MatchRuleParser().parse("match(eq,first)");
        result = validator.validate(context, element);
        assertTrue(result);
    }
    
    @Test
    public void testValidateNeq() {
        ValidateAttribute attribute = new ValidateAttribute("first", "eric1", "");
        context.addAttribute("first", attribute);
        
        validator = new MatchRuleParser().parse("match(neq,first)");
        result = validator.validate(context, element);
        assertTrue(result);
    }

    @Test
    public void testValidateLt() {
        ValidateAttribute attribute = new ValidateAttribute("first", "eric1", "");
        context.addAttribute("first", attribute);
        
        validator = new MatchRuleParser().parse("match(lt,first)");
        result = validator.validate(context, element);
        assertTrue(result);
    }
    
    @Test
    public void testValidateLte() {
        ValidateAttribute attribute = new ValidateAttribute("first", "eric1", "");
        context.addAttribute("first", attribute);
        
        validator = new MatchRuleParser().parse("match(lte,first)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        attribute = new ValidateAttribute("first", "eric", "");
        context.addAttribute("first", attribute);
        validator = new MatchRuleParser().parse("match(lte,first)");
        result = validator.validate(context, element);
        assertTrue(result);
    }
    
    @Test
    public void testValidateGt() {
        ValidateAttribute attribute = new ValidateAttribute("first", "aeric1", "");
        context.addAttribute("first", attribute);
        
        validator = new MatchRuleParser().parse("match(gt,first)");
        result = validator.validate(context, element);
        assertTrue(result);
    }
    
    @Test
    public void testValidateGte() {
        ValidateAttribute attribute = new ValidateAttribute("first", "aeric", "");
        context.addAttribute("first", attribute);
        
        validator = new MatchRuleParser().parse("match(gte,first)");
        result = validator.validate(context, element);
        assertTrue(result);
        
        attribute = new ValidateAttribute("first", "eric", "");
        context.addAttribute("first", attribute);
        validator = new MatchRuleParser().parse("match(gte,first)");
        result = validator.validate(context, element);
        assertTrue(result);
    }
}
