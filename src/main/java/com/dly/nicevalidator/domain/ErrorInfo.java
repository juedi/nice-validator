package com.dly.nicevalidator.domain;

/**
 * @typename ErrorInfo
 * @brief 校验失败的错误信息
 * @author dly
 * @date 2018年6月1日 下午1:52:31
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorInfo {

	/**属性名称*/
	private String attributeName;
	
	/**错误信息*/
	private String message;

	public ErrorInfo(String attributeName, String message) {
		this.attributeName = attributeName;
		this.message = message;
	}

	public String getAttribute() {
		return attributeName;
	}

	public void setAttribute(String attribute) {
		this.attributeName = attribute;
	}

	public String getError() {
		return message;
	}

	public void setError(String error) {
		this.message = error;
	}

	@Override
	public String toString() {
		return "{\"attribute\":\"" + attributeName + "\", \"error\":\"" + message + "\"}";
	}
	
}
