package com.dly.nicevalidator.parser;

import com.dly.nicevalidator.Validator;

/**
 * @typename RuleParser
 * @brief 校验规则解析
 * @author dly
 * @date 2018年6月1日 上午10:30:58
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RuleParser {

	/**
	 * 将校验规则的表达式解析成为校验器
	 * @param ruleExpression 校验规则表达式
	 * @return 校验器
	 */
	Validator<?> parse(String ruleExpression);
}
