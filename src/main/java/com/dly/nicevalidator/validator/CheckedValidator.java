package com.dly.nicevalidator.validator;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dly.nicevalidator.ValidateAttribute;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;

/**
 * @typename CheckedValidator
 * @brief 单选、多选情况下使用 
 * @author dly
 * @date 2018年6月4日 下午6:40:15
 * @version 1.0.0
 * @since 1.0.0
 */
public class CheckedValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckedValidator.class);
    
    private Integer min;
    private Integer max;
    
	/**
     * 创建一个新的实例 CheckedValidator.
     *
     * @param min 最小值
     * @param max 最大值
     */
    public CheckedValidator(Integer min, Integer max) {
        if((min != null && min < 1)
                || (max != null && max < 1)) {
            LOGGER.error("参数应为正整数");
            throw new IllegalArgumentException("参数应为正整数");
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
		if(element.getValue() == null){
		    context.addError(new ErrorInfo(element.getAttributeName(), "必选"));
		    return false;
		}
		
		int length = 0;
		if(element.getValue() instanceof Collection){
		    @SuppressWarnings("rawtypes")
            Collection value = (Collection) element.getValue();
		    length = value.size();
		}else if(element.getValue().getClass().isArray()){
		    Object[] value = (Object[]) element.getValue();
		    length = value.length;
		}else {
		    context.addError(new ErrorInfo(element.getAttributeName(), "数据类型不正确"));
		    return false;
		}
		
		if(length == 0){
		    context.addError(new ErrorInfo(element.getAttributeName(), "必选"));
		    return false;
		}
		
		if(min == null && max != null && length > max){
		    context.addError(new ErrorInfo(element.getAttributeName(), String.format("最多选择%s项", max)));
            return false;
		}else if(min != null && max == null && length < min){
		    context.addError(new ErrorInfo(element.getAttributeName(), String.format("至少选择%s项", min)));
            return false;
		}else if(min != null && min == max && min != max){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("必选 %s项", min)));
            return false;
        }else if(min != null && max != null && (length < min || length > max)){
            context.addError(new ErrorInfo(element.getAttributeName(), String.format("选择%s到 %s项", min, max)));
            return false;
        }
        
		return true;
	}

}
