package com.dly.nicevalidator.validator;

import com.dly.nicevalidator.ErrorMsg;
import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.ValidatorElement;

/**
 * @typename LengthValidator
 * @brief TODO 
 * @author dly
 * @date 2018年5月31日 下午3:53:31
 * @version 1.0.0
 * @since 1.0.0
 */
public class LengthValidator implements Validator<String>{

	private int min;
	
	private int max;
	
	public static final int INFINITY = -1;
	
	public LengthValidator(int min, int max) {
		
		if(Integer.max(min, max) <= 0) {
			throw new IllegalArgumentException("参数应为正整数");
		}
		
		if(max != INFINITY && min > max) {
			throw new IllegalArgumentException("最大值不能小于最小值");
		}
		this.min = min;
		this.max = max;
	}
	

	@Override
	public boolean validate(ValidatorContext context, ValidatorChain chain, ValidatorElement<String> element) {
		System.out.println(context.getAttribute("birthday"));
		if(element.getValue() == null && min != 0){
			context.addError(new ErrorMsg(element.getAttribute(), "请填写至少" + min + "个字符"));
			return false;
		} 
		
		int length = element.getValue().length();
		if(min == max && length != min) {
			context.addError(new ErrorMsg(element.getAttribute(), "请填写" + min + "个字符"));
			return false;
		}
		
		if(min == INFINITY && length > max) {
			context.addError(new ErrorMsg(element.getAttribute(), "请最多填写" + max + "个字符"));
			return false;
		}
		
		if(max == INFINITY && length < min) {
			context.addError(new ErrorMsg(element.getAttribute(), "请填写至少" + min + "个字符"));
			return false;
		}
		
		if(max != INFINITY && min != INFINITY && (length < min || length > max)) {
			context.addError(new ErrorMsg(element.getAttribute(), "请填写" + min + "到" + max + "个字符"));
			return false;
		}
		
		chain.validate(context, element);
		return true;
	}

}
