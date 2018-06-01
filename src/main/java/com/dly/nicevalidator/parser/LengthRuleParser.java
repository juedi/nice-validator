package com.dly.nicevalidator.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dly.nicevalidator.Validator;
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

	private static final Pattern LENGTH_REG_EQ = Pattern.compile("length\\((\\d+)\\)");
	private static final Pattern LENGTH_REG_GT = Pattern.compile("length\\((\\d+)~\\)");
	private static final Pattern LENGTH_REG_LT = Pattern.compile("length\\(~(\\d+)\\)");
	private static final Pattern LENGTH_REG_BW = Pattern.compile("length\\((\\d+)~(\\d+)\\)");
	
	@Override
	public Validator<?> parse(String ruleExpression) {
		if(LENGTH_REG_EQ.matcher(ruleExpression).matches()) {
			Matcher matcher = LENGTH_REG_EQ.matcher(ruleExpression);
			if(matcher.find()) {
				return new LengthValidator(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(1)));
			}
		}else if(LENGTH_REG_GT.matcher(ruleExpression).matches()) {
			Matcher matcher = LENGTH_REG_GT.matcher(ruleExpression);
			if(matcher.find()) {
				return new LengthValidator(Integer.parseInt(matcher.group(1)), LengthValidator.INFINITY);
			}
		}else if(LENGTH_REG_LT.matcher(ruleExpression).matches()) {
			Matcher matcher = LENGTH_REG_LT.matcher(ruleExpression);
			if(matcher.find()) {
				return new LengthValidator(LengthValidator.INFINITY, Integer.parseInt(matcher.group(1)));
			}
		}else if(LENGTH_REG_BW.matcher(ruleExpression).matches()) {
			Matcher matcher = LENGTH_REG_BW.matcher(ruleExpression);
			if(matcher.find()) {
				return new LengthValidator(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
			}
		}
		throw new ParseException("表达式[" + ruleExpression + "]解析失败，不支持的表达式");
	}
	
}
