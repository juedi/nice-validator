package com.dly.nicevalidator.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dly.nicevalidator.enums.RegexRuleEnum;

/** 
 * @typename RegexRuleTest
 * @brief 正则校验规则枚举测试类
 * @author dly
 * @date 2018年6月7日 上午10:01:48
 * @version 1.0.0
 * @since
 * 
 */
public class RegexRuleEnumTest {

    @Test
    public void testMatch() {
       boolean result = RegexRuleEnum.REGEX.match("regex(\\d+)");
       assertTrue(result);
       
       result = RegexRuleEnum.REGEX.match("regex(\"dbdcd\")");
       assertTrue(result);
       
       result = RegexRuleEnum.REGEX.match("regex(\\d+");
       assertFalse(result);
    }

    @Test
    public void testGetParameters() {
        String[] param = RegexRuleEnum.REGEX.getParameters("regex(\\d+)");
        assertEquals("\\d+", param[0]);
        
        param = RegexRuleEnum.REGEX.getParameters("regex(\"dbdcd\")");
        
        assertEquals("\"dbdcd\"", param[0]);
    }

}
