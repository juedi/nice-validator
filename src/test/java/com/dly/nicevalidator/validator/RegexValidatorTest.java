package com.dly.nicevalidator.validator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.parser.RegexRuleParser;

/** 
 * @typename RegexValidatorTest
 * @brief 正则表达式校验器
 * @author dly
 * @date 2018年6月7日 上午10:06:44
 * @version 1.0.0
 * @since
 * 
 */
public class RegexValidatorTest {
    
    private ValidatorContext context;
    private ValidateAttribute element;
    
    @Before
    public void setUp(){
        context = new ValidatorContext();
        element = new ValidateAttribute("rete", 20.33, "");
    }
    
    @Test
    public void testValidate() {
        Validator validator = new RegexRuleParser().parse("regex((-?\\d+)(\\.\\d+)?)");
        boolean result = validator.validate(context, element);
        assertTrue(result);
        
    }

}
