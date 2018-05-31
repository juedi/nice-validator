package com.dly.nicevalidator;

import java.util.ArrayList;
import java.util.List;

/**
 * @typename ValidationResult
 * @brief 验证结果
 * @author dly
 * @date 2018年5月31日 下午2:05:09
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidationResult {

	/**是否验证成功*/
	private boolean success = true;
	
	/**验证的错误信息*/
	private List<String> errors = new ArrayList<>();
	
	@Override
	public String toString() {
		return "ValidationResult [success=" + success + ", errors=" + errors + "]";
	}
	
	/**
	 * 添加错误信息
	 * @param error 错误信息
	 */
	public void addError(String error) {
		if(errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(error);
		this.success = false;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
