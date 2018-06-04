package com.dly.nicevalidator.validator;

import com.dly.nicevalidator.Validator;
import com.dly.nicevalidator.ValidatorChain;
import com.dly.nicevalidator.ValidatorContext;
import com.dly.nicevalidator.domain.ErrorInfo;
import com.dly.nicevalidator.domain.ValidatorElement;

/**
 * @typename CarValidator
 * @brief 汽车校验器 
 * @author dly
 * @date 2018年6月1日 下午4:55:48
 * @version 1.0.0
 * @since 1.0.0
 */
public class CarValidator implements Validator {

	@Override
	public boolean validate(ValidatorContext context, ValidatorChain chain, ValidatorElement element) {
		Car car = (Car) element.getValue();
		if(car.getPrice() > 10 || car.getWheel() != 4 || !"涡轮增加".equals(car.getTurbo())) {
			context.addError(new ErrorInfo(element.getAttributeName(), "校验失败"));
			return false;
		}
		
		chain.validate(context, element);
		return true;
	}

}
