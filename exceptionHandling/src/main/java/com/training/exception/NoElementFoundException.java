package com.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
public class NoElementFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1797557313976264316L;
	private String errorCode;
	private String errorMessage;
	public NoElementFoundException() {
		super();
	}
	public NoElementFoundException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	

}