package com.dly.nicevalidator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.enums.RegexRuleEnum;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.RegexValidator;

/** 
 * @typename: RegexRuleParser
 * @brief: 正则校验表达式解析器
 * @author: dly
 * @date: 2018年6月5日 上午11:34:26
 * @version: 1.0.0
 * @since
 * 
 */
public class RegexRuleParser implements RuleParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RegexRuleParser.class);
    
    @Override
    public Validator parse(String ruleExpression) {
        if(RegexRuleEnum.REGEX.match(ruleExpression)){
            String[] parameters = RegexRuleEnum.REGEX.getParameters(ruleExpression);
            return new RegexValidator(parameters[0]);
        }
        
        LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
        throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
    }

}
