package com.dly.nicevalidator.domain;

/**
 * @typename SimpleValidateAttribute
 * @brief 属性配置信息 
 * @author dly
 * @date 2018年6月4日 上午10:00:52
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidateAttribute{
	
	public static final String TYPE_NUMBER = "number";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_DATE = "date";

	/**属性名*/
	private String attributeName;
	/**校验表达式*/
	private String validateExpression;
	/**属性值*/
	private Object value;
	/**字段类型*/
	private String type;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	
}
