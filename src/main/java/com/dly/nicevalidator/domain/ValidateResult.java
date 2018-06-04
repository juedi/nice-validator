package com.dly.nicevalidator.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @typename ValidateResult
 * @brief 验证结果
 * @author dly
 * @date 2018年5月31日 下午2:05:09
 * @version 1.0.0
 * @since 1.0.0
 */
public class ValidateResult {

	/**是否验证成功*/
	private boolean success = true;
	
	/**验证的错误信息*/
	private List<ErrorInfo> errors;
	
	@Override
	public String toString() {
		return "{\"success\":" + success + ", \"errors\":" + errors + "}";
	}
	
	/**
	 * 添加错误信息
	 * @param error 错误信息
	 */
	public void addError(ErrorInfo error) {
		if(errors == null) {
			errors = new ArrayList<>();
		}
		errors.add(error);
		setSuccess(false);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<ErrorInfo> getErrors() {
		return errors;
	}

}
