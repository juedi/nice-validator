package com.dly.nicevalidator;

import com.dly.nicevalidator.domain.ValidateAttribute;

/**
 * @typename ConvertToValidateAttributeAble
 * @brief 校验属性接口, 外部对象实现这个接口，可以直接拿来使用 
 * @author dly
 * @date 2018年6月4日 上午10:20:37
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ConvertToValidateAttributeAble {

	/**
	 * @return 校验属性对象
	 */
	ValidateAttribute convertToValidateAttribute();
}
