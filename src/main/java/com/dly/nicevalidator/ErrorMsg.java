package com.dly.nicevalidator;

/**
 * @typename ErrorMsg
 * @brief 校验失败的错误信息
 * @author dly
 * @date 2018年6月1日 下午1:52:31
 * @version 1.0.0
 * @since 1.0.0
 */
/**
 * @typename ErrorMsg
 * @brief TODO 
 * @author dly
 * @date 2018年6月1日 下午1:55:25
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorMsg {

	/**属性名称*/
	private String attribute;
	
	/**错误信息*/
	private String error;

	public ErrorMsg(String attribute, String error) {
		this.attribute = attribute;
		this.error = error;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "{\"attribute\":\"" + attribute + "\", \"error\":\"" + error + "\"}";
	}
	
}
