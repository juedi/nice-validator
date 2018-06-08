package com.dly.nicevalidator.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dly.nicevalidator.enums.CheckedRuleEnum;

/** 
 * @typename CheckedRuleEnumTest
 * @brief checked规则枚举测试
 * @author dly
 * @date 2018年6月7日 下午3:45:10
 * @version 1.0.0
 * @since
 * 
 */
public class CheckedRuleEnumTest {

    @Test
    public void testMatch() {
        assertTrue(CheckedRuleEnum.CHECKED.match("checked"));
        assertTrue(CheckedRuleEnum.CHECKED_BT.match("checked(1~20)"));
        assertTrue(CheckedRuleEnum.CHECKED_EQ.match("checked(10)"));
        assertTrue(CheckedRuleEnum.CHECKED_GT.match("checked(2~)"));
        assertTrue(CheckedRuleEnum.CHECKED_LT.match("checked(~2)"));
    }

    @Test
    public void testGetParameters() {
        String[] params = CheckedRuleEnum.CHECKED.getParameters("checked");
        assertEquals(0, params.length);
        
        params = CheckedRuleEnum.CHECKED_EQ.getParameters("checked(3)");
        assertEquals("3", params[0]);
        
        params = CheckedRuleEnum.CHECKED_GT.getParameters("checked(3~)");
        assertEquals("3", params[0]);
        
        params = CheckedRuleEnum.CHECKED_LT.getParameters("checked(~3)");
        assertEquals("3", params[0]);
        
        params = CheckedRuleEnum.CHECKED_BT.getParameters("checked(1~3)");
        assertEquals("1", params[0]);
        assertEquals("3", params[1]);
        
    }

}
