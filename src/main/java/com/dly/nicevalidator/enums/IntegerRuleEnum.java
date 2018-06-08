package com.dly.nicevalidator.enums;

import java.util.regex.Pattern;

/** 
 * @typename IntegerRule
 * @brief 整形校验规则枚举
 * @author dly
 * @date 2018年6月6日 上午10:19:17
 * @version 1.0.0
 * @since
 * 
 */
public enum IntegerRuleEnum {

    /**整形数据*/
    INTEGER("integer"),
    /**正整数*/
    INTEGER_POSITIVE("integer\\(\\+\\)"),
    /**正整数和0*/
    INTEGER_POSITIVE_AND_ZERO("integer\\(\\+0\\)"),
    /**负整数*/
    INTEGER_NEGATIVE("integer\\(-\\)"),
    /**负整数和0*/
    INTEGER_NEGATIVE_AND_ZERO("integer\\(-0\\)");
    
    private Pattern pattern;
    
    private IntegerRuleEnum(String regex){
        this.pattern = Pattern.compile(regex);
    }
    
    /**    
     * @Description 校验规则是否匹配
     * @param ruleExpression 校验表达式
     * @return true-匹配；false-不匹配
     */
    public boolean match(String ruleExpression){
        return pattern.matcher(ruleExpression).matches();
    }
    
}
