package com.dly.nicevalidator.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @typename LengthRule
 * @brief 长度校验规则枚举
 * @author dly
 * @date 2018年6月6日 下午5:09:10
 * @version 1.0.0
 * @since
 * 
 */
public enum LengthRuleEnum{
    /**大于*/
    LENGTH_GT("length\\((\\d+)~\\)"),
    /**小于*/
    LENGTH_LT("length\\(~(\\d+)\\)"),
    /**等于*/
    LENGTH_EQ("length\\((\\d+)\\)"),
    /**在n1和n2之间*/
    LENGTH_BT("length\\((\\d+)~(\\d+)\\)");
    
    private Pattern pattern;
    
    private LengthRuleEnum(String regex) {
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
    
    /**    
     * @Description 获取表达式中的参数值
     * @param ruleExpression 校验表达式
     * @return 参数值数组
     */
    public String[] getParameters(String ruleExpression){
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
}
