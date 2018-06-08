package com.dly.nicevalidator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.enums.MatchRuleEnum;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.MatchValidator;

/** 
 * @typename: MatchRuleParser
 * @brief: 比较规则解析器
 * @author: dly
 * @date: 2018年6月5日 上午11:33:26
 * @version: 1.0.0
 * @since
 * 
 */
public class MatchRuleParser implements RuleParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MatchRuleParser.class);
    
    @Override
    public Validator parse(String ruleExpression) {
        if(MatchRuleEnum.MATCH.match(ruleExpression)){
            String[] parameters = MatchRuleEnum.MATCH.getParameters(ruleExpression);
            boolean isEmpty = parameters[0] == null || "".equals(parameters[0]);
            return new MatchValidator(isEmpty ? "eq" : parameters[0], parameters[1]);
        }
        
        LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
        throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
    }

}
