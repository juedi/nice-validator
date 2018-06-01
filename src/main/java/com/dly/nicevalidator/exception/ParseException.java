package com.dly.nicevalidator.exception;

/**
 * @typename ParseException
 * @brief 表达式解析异常
 * @author dly
 * @date 2018年6月1日 上午11:11:39
 * @version 1.0.0
 * @since 1.0.0
 */
public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParseException(String message) {
		super(message);
	}
}
