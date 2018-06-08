package com.dly.nicevalidator.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.RuleParser;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.enums.LengthRuleEnum;
import com.dly.nicevalidator.exception.ParseException;
import com.dly.nicevalidator.validator.LengthValidator;

/**
 * @typename LengthRuleParser
 * @brief 长度类型的规则校验 ，支持<br/>
 * length(n)	请填写 n 个字符<br/>
 * length(n~)	请至少填写 n 个字符<br/>
 * length(~n)	请最多填写 n 个字符<br/>
 * length(n1~n2) 请填写 n1 到 n2 个字符<br/>
 * @author dly
 * @date 2018年6月1日 上午10:35:55
 * @version 1.0.0
 * @since 1.0.0
 */
public class LengthRuleParser implements RuleParser {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LengthRuleParser.class);
    
	@Override
	public Validator parse(String ruleExpression) {
		if(LengthRuleEnum.LENGTH_EQ.match(ruleExpression)) {
		    String[] parameters = LengthRuleEnum.LENGTH_EQ.getParameters(ruleExpression);
			return new LengthValidator(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[0]));
		}
		
		else if(LengthRuleEnum.LENGTH_GT.match(ruleExpression)) {
		    String[] parameters = LengthRuleEnum.LENGTH_GT.getParameters(ruleExpression);
            return new LengthValidator(Integer.parseInt(parameters[0]), null);
		}
		
		else if(LengthRuleEnum.LENGTH_LT.match(ruleExpression)) {
		    String[] parameters = LengthRuleEnum.LENGTH_LT.getParameters(ruleExpression);
            return new LengthValidator(null, Integer.parseInt(parameters[0]));
		}
		
		else if(LengthRuleEnum.LENGTH_BT.match(ruleExpression)) {
		    String[] parameters = LengthRuleEnum.LENGTH_BT.getParameters(ruleExpression);
		    return new LengthValidator(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]));
		}
		LOGGER.error("表达式[{}]解析失败，不支持的表达式", ruleExpression);
		throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
	}
	
}
