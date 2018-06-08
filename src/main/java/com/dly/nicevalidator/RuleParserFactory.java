package com.dly.nicevalidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.parser.CheckedRuleParser;
import com.dly.nicevalidator.parser.CustomRuleParser;
import com.dly.nicevalidator.parser.IntegerRuleParser;
import com.dly.nicevalidator.parser.LengthRuleParser;
import com.dly.nicevalidator.parser.MatchRuleParser;
import com.dly.nicevalidator.parser.RangeRuleParser;
import com.dly.nicevalidator.parser.RegexRuleParser;
import com.dly.nicevalidator.parser.RequiredRuleParser;

/** 
 * @typename: RuleParserFactory
 * @brief: 规则解析工厂类，按照校验表达式生成规则解析器
 * @author: dly
 * @date: 2018年6月5日 下午2:12:23
 * @version: 1.0.0
 * @since
 * 
 */
public class RuleParserFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleParserFactory.class);
    
    /**    
     * @Description: 获取规则解析器的实例
     * @param expression 校验规则表达式
     * @return 校验规则解析器
     */
    public static RuleParser getRuleParser(String expression){
        if(expression.startsWith("required")){
            return new RequiredRuleParser();
        }else if(expression.startsWith("length")){
            return new LengthRuleParser();
        }else if(expression.startsWith("range")){
            return new RangeRuleParser();
        }else if(expression.startsWith("checked")){
            return new CheckedRuleParser();
        }else if(expression.startsWith("custom")){
            return new CustomRuleParser();
        }else if(expression.startsWith("integer")){
            return new IntegerRuleParser();
        }else if(expression.startsWith("match")){
            return new MatchRuleParser();
        }else if(expression.startsWith("regex")){
            return new RegexRuleParser();
        }
        
        LOGGER.error("不支持的表达式");
        throw new ParseException("不支持的表达式");
    }
}
