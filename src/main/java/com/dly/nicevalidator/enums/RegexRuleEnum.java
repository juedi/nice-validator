package com.dly.nicevalidator.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @typename RegexRule
 * @brief 正则校验规则
 * @author dly
 * @date 2018年6月6日 下午5:51:21
 * @version 1.0.0
 * @since
 * 
 */
public enum RegexRuleEnum {

    /**正则表达式*/
    REGEX("regex\\((.+)\\)");
    
    private Pattern pattern;
    private RegexRuleEnum(String regex){
        this.pattern = Pattern.compile(regex);
    }
    
    /**    
     * @Description 规则是否匹配
     * @param ruleExpression 校验表达式
     * @return 符合规则返回true，不符合返回false
     */
    public boolean match(String ruleExpression){
        return this.pattern.matcher(ruleExpression).matches();
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
