package com.mainiway.exception;


/**
 * accessToken 认证失败
 * @author heli
 *
 */
public class NoTokenException extends RuntimeException {

	public NoTokenException(String msg) {
		super(msg);
	}
	public NoTokenException(String msg,Throwable e) {
		super(msg, e);
	}
}
