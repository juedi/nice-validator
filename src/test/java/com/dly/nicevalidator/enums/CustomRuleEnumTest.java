package com.dly.nicevalidator.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dly.nicevalidator.enums.CustomRuleEnum;

/** 
 * @typename CustomRuleEnumTest
 * @brief 自定义校验规则枚举测试
 * @author dly
 * @date 2018年6月7日 上午10:37:19
 * @version 1.0.0
 * @since
 * 
 */
public class CustomRuleEnumTest {

    @Test
    public void testMatch() {
        boolean result = CustomRuleEnum.CUSTOM_CLASS.match("custom(com.dly.cc.Validator)");
        assertTrue(result);
        
        result = CustomRuleEnum.CUSTOM_CLASS.match("custom(com.dly.cc.Validator--)");
        assertFalse(result);
    }

    @Test
    public void testGetParameters() {
        String[] params = CustomRuleEnum.CUSTOM_CLASS.getParameters("custom(com.dly.cc.Validator)");
        assertEquals("com.dly.cc.Validator", params[0]);
        
    }

}
