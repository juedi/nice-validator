package com.dly.nicevalidator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.enums.CheckedRuleEnum;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.CheckedValidator;

/** 
 * @typename: CheckedRuleParser
 * @brief: checked校验规则解析器
 * @author: dly
 * @date: 2018年6月5日 上午11:33:00
 * @version: 1.0.0
 * @since
 * 
 */
public class CheckedRuleParser implements RuleParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckedRuleParser.class);
    
    @Override
    public Validator parse(String ruleExpression) {
        if(CheckedRuleEnum.CHECKED.match(ruleExpression)){
            return new CheckedValidator(null, null);
        }
        
        else if(CheckedRuleEnum.CHECKED_EQ.match(ruleExpression)){
            String[] parameters = CheckedRuleEnum.CHECKED_EQ.getParameters(ruleExpression);
            return new CheckedValidator(Integer.parseInt(parameters[0]), null);
        }
        
        else if(CheckedRuleEnum.CHECKED_LT.match(ruleExpression)){
            String[] parameters = CheckedRuleEnum.CHECKED_LT.getParameters(ruleExpression);
            return new CheckedValidator(null, Integer.parseInt(parameters[0]));
        }
        
        else if(CheckedRuleEnum.CHECKED_GT.match(ruleExpression)){
            String[] parameters = CheckedRuleEnum.CHECKED_GT.getParameters(ruleExpression);
            return new CheckedValidator(Integer.parseInt(parameters[0]), null);
        }
        
        else if(CheckedRuleEnum.CHECKED_BT.match(ruleExpression)){
            String[] parameters = CheckedRuleEnum.CHECKED_BT.getParameters(ruleExpression);
            return new CheckedValidator(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]));
        }
        
        LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
        throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
    }

}
