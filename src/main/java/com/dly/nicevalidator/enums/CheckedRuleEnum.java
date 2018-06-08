package com.dly.nicevalidator.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @typename CheckedRuleEnum
 * @brief Checked校验规则枚举
 * @author dly
 * @date 2018年6月7日 下午3:09:26
 * @version 1.0.0
 * @since
 * 
 */
public enum CheckedRuleEnum {

    /**必选*/
    CHECKED("checked"),
    /**必选n*/
    CHECKED_EQ("checked\\((\\d+)\\)"),
    /**大于n*/
    CHECKED_GT("checked\\((\\d+)~\\)"),
    /**小于n*/
    CHECKED_LT("checked\\(~(\\d+)\\)"),
    /**大于n1小于n2*/
    CHECKED_BT("checked\\((\\d+)~(\\d+)\\)");
    
    private Pattern pattern;
    
    private CheckedRuleEnum(String regex){
        this.pattern = Pattern.compile(regex);
    }
    
    /**    
     * @Description 规则是否匹配
     * @param ruleExpression 校验表达式
     * @return 符合规则返回true，不符合返回false
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
