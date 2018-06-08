package com.dly.nicevalidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @typename SimpleValidateAttribute
 * @brief 属性配置信息 
 * @author dly
 * @date 2018年6月4日 上午10:00:52
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidateAttribute{
	
	/**属性名*/
	private String attributeName;
	/**校验表达式*/
	private String validateExpression;
	/**属性值*/
	private Object value;
	/**校验器链，每个属性会由多个校验器进行校验*/
    private List<Validator> validators;
    /**是否进行校验，默认值是true*/
    private boolean doValidate = true;
	
	/**
     * 创建一个新的实例 ValidateAttribute.
     *
     * @param attributeName 属性名
     * @param value 属性值
     * @param validateExpression 校验表达式
     */
    public ValidateAttribute(String attributeName, Object value, String validateExpression) {
        this(attributeName, value, validateExpression, true);
    }
    
    /**
     * 创建一个新的实例 ValidateAttribute.
     *
     * @param attributeName         属性名称
     * @param value                 属性值
     * @param validateExpression    校验表达式
     * @param doValiate             是否校验当前属性
     */
    public ValidateAttribute(String attributeName, Object value, String validateExpression, boolean doValiate) {
        this.attributeName = attributeName;
        this.validateExpression = validateExpression;
        this.value = value;
        this.doValidate = doValiate;
        if(doValiate){
            initValidator();
        }
    }
    public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getValidateExpression() {
		return validateExpression;
	}
	public void setValidateExpression(String validateExpression) {
		this.validateExpression = validateExpression;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
    public boolean isDoValidate() {
        return doValidate;
    }
    
    public void setDoValidate(boolean doValidate) {
        this.doValidate = doValidate;
    }
    
    /**    
     * @Description: 初始化校验器  
     */
    private void initValidator(){
	    if(validateExpression == null || "".equals(validateExpression.trim())) {
	        this.validators = Collections.emptyList();
        }else{
            String[] expressions = validateExpression.split(";");
            
            List<Validator> validators = new ArrayList<>();
            
            for(String expression : expressions) {
                if(expression == null || "".equals(expression.trim())) {
                    continue;
                }
                
                validators.add(RuleParserFactory.getRuleParser(expression).parse(expression));
                
            }
            this.validators = validators;
        }
	    
	}
	
	/**    
	 * @Description: 开始校验当前属性  
	 * @param context 校验器上下文
	 * @return 校验结果 true-校验通过；false-校验不通过
	 */
	boolean doValidate(ValidatorContext context) {
	    //当前属性设置为不校验，直接跳过
	    if(!doValidate){
	        return true;
	    }
        for(Validator validator : validators){
            boolean result = validator.validate(context, this);
            //字段上面配置多个校验器时，采用快速失败模式
            if(!result){
                return false;
            }
        }
        return true;
    }
	
}
