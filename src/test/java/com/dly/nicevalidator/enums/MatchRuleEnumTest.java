package com.dly.nicevalidator.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.dly.nicevalidator.enums.MatchRuleEnum;

/** 
 * @typename MatchRuleEnumTest
 * @brief match规则枚举
 * @author dly
 * @date 2018年6月7日 下午4:02:31
 * @version 1.0.0
 * @since
 * 
 */
public class MatchRuleEnumTest {

    @Test
    public void testMatch() {
       assertTrue(MatchRuleEnum.MATCH.match("match(a)"));
       assertTrue(MatchRuleEnum.MATCH.match("match(eq,a)"));
       assertTrue(MatchRuleEnum.MATCH.match("match(neq,a)"));
       assertTrue(MatchRuleEnum.MATCH.match("match(lt,a)"));
       assertTrue(MatchRuleEnum.MATCH.match("match(lte,a)"));
       assertTrue(MatchRuleEnum.MATCH.match("match(gt,a)"));
       assertTrue(MatchRuleEnum.MATCH.match("match(gte,a)"));
       assertFalse(MatchRuleEnum.MATCH.match("match(gtea,a)"));
    }

    @Test
    public void testGetParameters() {
        String[] params = MatchRuleEnum.MATCH.getParameters("match(a)");
       assertEquals(null, params[0]);
       assertEquals("a", params[1]);
        
        params = MatchRuleEnum.MATCH.getParameters("match(eq,a)");
       assertEquals("eq", params[0]);
       assertEquals("a", params[1]);
        
        params = MatchRuleEnum.MATCH.getParameters("match(neq,a)");
       assertEquals("neq", params[0]);
       assertEquals("a", params[1]);
        
        params = MatchRuleEnum.MATCH.getParameters("match(lt,a)");
       assertEquals("lt", params[0]);
       assertEquals("a", params[1]);
        
        params = MatchRuleEnum.MATCH.getParameters("match(lte,a)");
       assertEquals("lte", params[0]);
       assertEquals("a", params[1]);
        
        params = MatchRuleEnum.MATCH.getParameters("match(gt,a)");
       assertEquals("gt", params[0]);
       assertEquals("a", params[1]);
        
        params = MatchRuleEnum.MATCH.getParameters("match(gte,a)");
       assertEquals("gte", params[0]);
       assertEquals("a", params[1]);
    }

}
