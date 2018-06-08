package com.dly.nicevalidator.parser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.enums.CustomRuleEnum;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.CustomValidator;

/**
 * @typename CustomRuleParser
 * @brief 自定义规则校验器 
 * @author dly
 * @date 2018年6月4日 上午11:12:31
 * @version 1.0.0
 * @since 1.0.0
 */
public class CustomRuleParser implements RuleParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomRuleParser.class);
    
	@Override
	public Validator parse(String ruleExpression) {
		if(CustomRuleEnum.CUSTOM_CLASS.match(ruleExpression)){
		    String[] parameters = CustomRuleEnum.CUSTOM_CLASS.getParameters(ruleExpression);
		    return new CustomValidator(parameters[0]);
		}
		LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
		throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
	}

}
