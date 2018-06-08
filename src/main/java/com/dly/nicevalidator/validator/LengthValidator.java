package com.dly.nicevalidator.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;

/**
 * @typename LengthValidator
 * @brief 长度校验器 
 * @author dly
 * @date 2018年5月31日 下午3:53:31
 * @version 1.0.0
 * @since 1.0.0
 */
public class LengthValidator implements Validator{

    private static final Logger LOGGER = LoggerFactory.getLogger(LengthValidator.class);
    
	private Integer min;
	
	private Integer max;
	
	/**
	 * 创建一个新的实例 LengthValidator.
	 *
	 * @param min 最小长度
	 * @param max 最大长度
	 */
	public LengthValidator(Integer min, Integer max) {
		
		if((min != null && min < 1)
		        || (max != null && max < 1)) {
		    LOGGER.error("参数应为正整数");
			throw new IllegalArgumentException("参数应为正整数");
		}
		
		if(min == null && max == null){
		    LOGGER.error("最大值和最小值不可同时为空");
		    throw new IllegalArgumentException("最大值和最小值不可同时为空");
		}
		
		if(min != null && max != null && min > max) {
		    LOGGER.error("最大值不能小于最小值");
			throw new IllegalArgumentException("最大值不能小于最小值");
		}
		this.min = min;
		this.max = max;
	}
	

	@Override
	public boolean validate(ValidatorContext context, ValidateAttribute element) {
		if(element.getValue() == null && min != 0){
			context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写至少%s个字符", min)));
			return false;
		} 
		
		int length = element.getValue().toString().length();
		if(min == max && length != min) {
			context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写%s个字符", min)));
			return false;
		}
		
		if(min == null && length > max) {
			context.addError(new ErrorInfo(element.getAttributeName(), String.format("请最多填写%s个字符", max)));
			return false;
		}
		
		if(max == null && length < min) {
			context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写至少%s个字符", min)));
			return false;
		}
		
		if(max != null && min != null && (length < min || length > max)) {
			context.addError(new ErrorInfo(element.getAttributeName(), String.format("请填写%s到%s个字符", min, max)));
			return false;
		}
		
		return true;
	}

}
