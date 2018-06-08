package com.dly.nicevalidator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.enums.IntegerRuleEnum;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.IntegerValidator;

/** 
 * @typename: IntegerRuleParser
 * @brief: 整形规则解析器
 * @author: dly
 * @date: 2018年6月5日 上午11:40:48
 * @version: 1.0.0
 * @since
 * 
 */
public class IntegerRuleParser implements RuleParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegerRuleParser.class);
    
    @Override
    public Validator parse(String ruleExpression) {
        
        if(IntegerRuleEnum.INTEGER.match(ruleExpression)){
            return new IntegerValidator(IntegerRuleEnum.INTEGER);
        }
        
        else if(IntegerRuleEnum.INTEGER_POSITIVE.match(ruleExpression)){
            return new IntegerValidator(IntegerRuleEnum.INTEGER_POSITIVE);
        }
        
        else if(IntegerRuleEnum.INTEGER_POSITIVE_AND_ZERO.match(ruleExpression)){
            return new IntegerValidator(IntegerRuleEnum.INTEGER_POSITIVE_AND_ZERO);
        }
        
        else if(IntegerRuleEnum.INTEGER_NEGATIVE.match(ruleExpression)){
            return new IntegerValidator(IntegerRuleEnum.INTEGER_NEGATIVE);
        }
        
        else if(IntegerRuleEnum.INTEGER_NEGATIVE_AND_ZERO.match(ruleExpression)){
            return new IntegerValidator(IntegerRuleEnum.INTEGER_NEGATIVE_AND_ZERO);
        }
        LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
        throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
    }

}
