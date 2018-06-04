package com.dly.nicevalidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dly.nicevalidator.parser.LengthRuleParser;

/**
 * @typename ParserFactory
 * @brief 表达式解析器工厂
 * @author dly
 * @date 2018年6月4日 上午10:56:04
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidatorFactory {

	public static List<Validator> createValidators(String validateExpressions){
		if(validateExpressions == null || "".equals(validateExpressions.trim())) {
			return Collections.emptyList();
		}
		
		String[] expressions = validateExpressions.split(";");
		List<Validator> validators = new ArrayList<>();
		for(String expression : expressions) {
			if(expression == null || "".equals(expression.trim())) {
				continue;
			}
			
			if(expression.startsWith("length")) {
				validators.add(new LengthRuleParser().parse(expression));
			}
		}
		
		return validators;
	}
}
