package com.dly.nicevalidator.parser;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.enums.RangeRuleEnum;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.RangeValidator;

/** 
 * @typename: RangeRuleParser
 * @brief: 范围校验表达式解析器
 * @author: dly
 * @date: 2018年6月5日 上午11:33:48
 * @version: 1.0.0
 * @since
 * 
 */
public class RangeRuleParser implements RuleParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(RangeRuleParser.class);
    
    @Override
    public Validator parse(String ruleExpression) {

        if(RangeRuleEnum.RANGE_GT.match(ruleExpression)){
            String[] parameters = RangeRuleEnum.RANGE_GT.getParameters(ruleExpression);
            return new RangeValidator(new BigDecimal(parameters[0]), null, parameters[3] == null ? true : Boolean.valueOf(parameters[3]));
        }
        
        else if(RangeRuleEnum.RANGE_LT.match(ruleExpression)){
            String[] parameters = RangeRuleEnum.RANGE_LT.getParameters(ruleExpression);
            return new RangeValidator(null, new BigDecimal(parameters[0]), parameters[3] == null ? true : Boolean.valueOf(parameters[3]));
        }
        
        else if(RangeRuleEnum.RANGE_BT.match(ruleExpression)){
            String[] parameters = RangeRuleEnum.RANGE_BT.getParameters(ruleExpression);
            return new RangeValidator(new BigDecimal(parameters[0]), new BigDecimal(parameters[3]), parameters[6] == null ? true : Boolean.valueOf(parameters[6]));
        }
        
        LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
        throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
    }
    
}
