package com.dly.nicevalidator.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dly.nicevalidator.enums.RangeRuleEnum;

/** 
 * @typename RangeRuleTest
 * @brief 范围规则枚举测试类
 * @author dly
 * @date 2018年6月6日 下午3:23:16
 * @version 1.0.0
 * @since
 * 
 */
public class RangeRuleEnumTest {

    @Test
    public void testMatch() {
        assertTrue(RangeRuleEnum.RANGE_BT.match("range(1~2)"));
        
        assertTrue(RangeRuleEnum.RANGE_BT.match("range(1~2, false )"));
        assertTrue(RangeRuleEnum.RANGE_BT.match("range(1~2,false)"));
        assertTrue(RangeRuleEnum.RANGE_BT.match("range(1~2)"));
        assertFalse(RangeRuleEnum.RANGE_BT.match("range(1~s)"));
        
        
        assertTrue(RangeRuleEnum.RANGE_GT.match("range(1~)"));
        assertFalse(RangeRuleEnum.RANGE_GT.match("range(1)"));
        
        assertTrue(RangeRuleEnum.RANGE_GT.match("range(1~, false)"));
        assertFalse(RangeRuleEnum.RANGE_GT.match("range(1~2)"));
        
        assertTrue(RangeRuleEnum.RANGE_LT.match("range(~1)"));
        assertFalse(RangeRuleEnum.RANGE_LT.match("range(1~2)"));
        
        assertTrue(RangeRuleEnum.RANGE_LT.match("range(~1, false)"));
        assertTrue(RangeRuleEnum.RANGE_LT.match("range(~2,true)"));
        
        assertTrue(RangeRuleEnum.RANGE_LT.match("range(~2)"));
        
    }

    @Test
    public void testGetParameters() {
        String[] result = RangeRuleEnum.RANGE_BT.getParameters("range(1~2)");
        assertEquals("1", result[0]);
        assertEquals("2", result[3]);
        
        result = RangeRuleEnum.RANGE_BT.getParameters("range(1~2, true)");
        assertEquals("1", result[0]);
        assertEquals("2", result[3]);
        assertEquals("true", result[6]);
        
        result = RangeRuleEnum.RANGE_BT.getParameters("range(1~2, false)");
        assertEquals("1", result[0]);
        assertEquals("2", result[3]);
        assertEquals("false", result[6]);
        
        result = RangeRuleEnum.RANGE_LT.getParameters("range(~2, false)");
        assertEquals("2", result[0]);
        assertEquals("false", result[3]);
        
        result = RangeRuleEnum.RANGE_LT.getParameters("range(~2, true)");
        assertEquals("2", result[0]);
        assertEquals("true", result[3]);
        
        result = RangeRuleEnum.RANGE_LT.getParameters("range(~2)");
        assertEquals("2", result[0]);
        
        result = RangeRuleEnum.RANGE_GT.getParameters("range(2~, false)");
        assertEquals("2", result[0]);
        assertEquals("false", result[3]);
        result = RangeRuleEnum.RANGE_GT.getParameters("range(2~, true)");
        assertEquals("2", result[0]);
        assertEquals("true", result[3]);
        result = RangeRuleEnum.RANGE_GT.getParameters("range(2~)");
        assertEquals("2", result[0]);
    }

}
