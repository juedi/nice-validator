package com.dly.nicevalidator;

/**
 * @typename Validator
 * @brief 验证器接口
 * @author dly
 * @date 2018年5月31日 上午11:21:20
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Validator<T> {

	boolean validate(ValidatorContext context, ValidatorChain chain, ValidatorElement<T> element);
}
