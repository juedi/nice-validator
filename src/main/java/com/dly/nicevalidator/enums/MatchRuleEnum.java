package com.dly.nicevalidator.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @typename MatchRuleEnum
 * @brief match规则枚举
 * @author dly
 * @date 2018年6月7日 下午1:49:55
 * @version 1.0.0
 * @since
 * 
 */
public enum MatchRuleEnum {

    /**match 匹配*/
    MATCH("match\\((eq|neq|lt|gt|lte|gte)?,?(\\w+)\\)", true),
    
    /**等于*/
    EQ("eq", false),
    /**不等于*/
    NEQ("neq", false),
    /**小于*/
    LT("lt", false),
    /**大于*/
    GT("gt", false),
    /**小于等于*/
    LTE("lte", false),
    /**大于等于*/
    GTE("gte", false);
    
    private Pattern pattern;
    private String operator;
    private boolean isRegex;
    
    /**
     * 创建一个新的实例 MatchRuleEnum.
     *
     * @param expression 表达式
     * @param isRegex 是否正则表达式
     */
    private MatchRuleEnum(String expression, boolean isRegex){
        if(isRegex){
            this.pattern = Pattern.compile(expression);
        }else{
            this.operator = expression;
        }
        this.isRegex = isRegex;
    }
    
    
    /**    
     * @Description 规则是否匹配
     * @param ruleExpression 校验表达式
     * @return 符合规则返回true，不符合返回false
     */
    public boolean match(String ruleExpression){
        if(!isRegex){
            throw new UnsupportedOperationException();
        }
        return pattern.matcher(ruleExpression).matches();
    }
    
    /**    
     * @Description 获取表达式中的参数值
     * @param ruleExpression 校验表达式
     * @return 参数值数组
     */
    public String[] getParameters(String ruleExpression){
        if(!isRegex){
            throw new UnsupportedOperationException();
        }
        Matcher matcher = pattern.matcher(ruleExpression);
        if(matcher.find()){
            int count = matcher.groupCount();
            String[] parameters = new String[count];
            for(int i = 0; i < count; i++){
                parameters[i] = matcher.group(i+1);
            }
            return parameters;
        }
        return new String[0];
    }
    
    /**    
     * @Description 判断类型是否一致
     * @param operator 操作
     * @return 是否一致，true-一致；false-不一致
     */
    public boolean is(String operator){
        if(isRegex){
            throw new UnsupportedOperationException();
        }
        return this.operator.equals(operator);
    }
}
