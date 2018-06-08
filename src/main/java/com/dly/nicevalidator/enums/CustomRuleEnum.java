package com.dly.nicevalidator.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @typename CustomRule
 * @brief 自定义校验规则枚举类
 * @author dly
 * @date 2018年6月7日 上午10:17:40
 * @version 1.0.0
 * @since
 * 
 */
public enum CustomRuleEnum {

    /**自定义类*/
    CUSTOM_CLASS("custom\\(((\\w|\\.)+)\\)");
    
    private Pattern pattern;
    
    private CustomRuleEnum(String regex){
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
