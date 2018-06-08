package com.dly.nicevalidator.enums;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dly.nicevalidator.enums.IntegerRuleEnum;

/** 
 * @typename IntegerRuleTest
 * @brief integer规则枚举类测试
 * @author dly
 * @date 2018年6月6日 上午10:32:24
 * @version 1.0.0
 * @since
 * 
 */
public class IntegerRuleEnumTest {

    @Test
    public void testMatch() {
        assertTrue(IntegerRuleEnum.INTEGER.match("integer"));
        assertTrue(IntegerRuleEnum.INTEGER_NEGATIVE.match("integer(-)"));
        assertTrue(IntegerRuleEnum.INTEGER_NEGATIVE_AND_ZERO.match("integer(-0)"));
        assertTrue(IntegerRuleEnum.INTEGER_POSITIVE.match("integer(+)"));
        assertTrue(IntegerRuleEnum.INTEGER_POSITIVE_AND_ZERO.match("integer(+0)"));
        
        assertFalse(IntegerRuleEnum.INTEGER.match("integer(0)"));
        assertFalse(IntegerRuleEnum.INTEGER_NEGATIVE.match("integer(-0)"));
        assertFalse(IntegerRuleEnum.INTEGER_NEGATIVE_AND_ZERO.match("integer(-)"));
        assertFalse(IntegerRuleEnum.INTEGER_POSITIVE.match("integer"));
        assertFalse(IntegerRuleEnum.INTEGER_POSITIVE_AND_ZERO.match("integer(0)"));
    }

}
