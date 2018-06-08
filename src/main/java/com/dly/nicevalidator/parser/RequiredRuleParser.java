package com.dly.nicevalidator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.RequiredValidator;

/** 
 * @typename: RequiredRuleParser
 * @brief: Required校验表达式解析器
 * @author: dly
 * @date: 2018年6月5日 上午11:34:55
 * @version: 1.0.0
 * @since
 * 
 */
public class RequiredRuleParser implements RuleParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequiredRuleParser.class);
    
    @Override
    public Validator parse(String ruleExpression) {
        if("required".equals(ruleExpression)){
            return new RequiredValidator();
        }
        LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
        throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
    }

}
