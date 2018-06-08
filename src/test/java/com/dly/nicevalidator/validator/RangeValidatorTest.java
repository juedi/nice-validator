package com.dly.nicevalidator.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.parser.RangeRuleParser;

/** 
 * @typename RangeValidatorTest
 * @brief 范围校验器测试类
 * @author dly
 * @date 2018年6月6日 下午4:17:49
 * @version 1.0.0
 * @since
 * 
 */
public class RangeValidatorTest {

    private ValidatorContext context;
    private ValidateAttribute element;
    
    @Before
    public void setUp(){
        context = new ValidatorContext();
        element = new ValidateAttribute("rete", 20.33, "");
    }
    
    @Test
    public void testValidate() {
        Validator validator = new RangeRuleParser().parse("range(20.33~)");
        boolean result = validator.validate(context, element);
        Assert.assertTrue(result);
        
        validator = new RangeRuleParser().parse("range(20.34~)");
        result = validator.validate(context, element);
        Assert.assertFalse(result);
        
        validator = new RangeRuleParser().parse("range(20.33~, false)");
        result = validator.validate(context, element);
        Assert.assertFalse(result);
        
        validator = new RangeRuleParser().parse("range(20.33~, true)");
        result = validator.validate(context, element);
        Assert.assertTrue(result);
        
        validator = new RangeRuleParser().parse("range(1~21)");
        result = validator.validate(context, element);
        Assert.assertTrue(result);
        
        validator = new RangeRuleParser().parse("range(1~21.332, true)");
        result = validator.validate(context, element);
        Assert.assertTrue(result);
        
        validator = new RangeRuleParser().parse("range(~21.332, true)");
        result = validator.validate(context, element);
        Assert.assertTrue(result);
        
        validator = new RangeRuleParser().parse("range(~21.332, false)");
        result = validator.validate(context, element);
        Assert.assertTrue(result);
        
        validator = new RangeRuleParser().parse("range(~20.33, false)");
        result = validator.validate(context, element);
        Assert.assertFalse(result);
        
        validator = new RangeRuleParser().parse("range(20.33~20.33)");
        result = validator.validate(context, element);
        Assert.assertTrue(result);
        
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testValidateWithException(){
        Validator validator = new RangeRuleParser().parse("range(20.33~20.33, false)");
        boolean result = validator.validate(context, element);
        Assert.assertFalse(result);
    }
    
    @Test(expected=ParseException.class)
    public void testValidateWithParseException(){
        Validator validator = new RangeRuleParser().parse("range(20.33~20.33, fase)");
        boolean result = validator.validate(context, element);
        Assert.assertFalse(result);
    }

}
