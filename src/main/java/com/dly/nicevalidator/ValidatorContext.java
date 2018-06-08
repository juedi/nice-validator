package com.dly.nicevalidator;

import java.util.HashMap;
import java.util.Map;

import com.dly.nicevalidator.domain.ErrorInfo;
import com.dly.nicevalidator.domain.ValidateResult;

/**
 * @typename ValidatorContext
 * @brief 校验器上下文 
 * @author dly
 * @date 2018年5月31日 下午1:39:16
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidatorContext {

	/**
     * 验证器均可以共享使用的属性键值对
     */
    private Map<String, Object> attributes;
    
    private final ValidateResult result = new ValidateResult();
    
    /**
     * 获取属性
     * @param key 键
     * @return    值
     */
    public Object getAttribute(String key) {
        if (attributes != null && !attributes.isEmpty()) {
            return attributes.get(key);
        }
        return null;
    }
    
    /**
     * 添加属性到校验器上下文中 
     * @param key	键
     * @param value 值
     */
    public void addAttribute(String key, Object value) {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        attributes.put(key, value);
    }
    
    /**    
     * @Description:        添加多个属性到校验器上下文中  
     * @param attributes    属性集合
     */
    public void addAttributes(Map<String, Object> attributes) {
        if (attributes == null) {
            attributes = new HashMap<>();
        }
        attributes.putAll(attributes);
    }
    
    /**
     * 代理validationResult添加错误信息
     * @param error 错误信息
     */
    public void addError(ErrorInfo error) {
    	this.result.addError(error);
    }

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public ValidateResult getResult() {
		return result;
	}
}
