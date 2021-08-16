package com.training.exception;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException
{

	private static final long serialVersionUID = 1L;


	private HttpStatus httpStatus;
	private String errorCode;
	private String errorModule;//=ExceptionConstants.GENERAL_MODULE;
	private String exceptionMessage;
	private String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
	
	public BaseException(String errorCode, String errorModule, String exceptionMessage) {
		super();
		this.errorCode = errorCode;
		this.errorModule = errorModule;
		this.exceptionMessage = exceptionMessage;
	}


	public BaseException(HttpStatus httpStatus, String errorCode, String errorModule, String exceptionMessage,
			String timeStamp) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.errorModule = errorModule;
		this.exceptionMessage = exceptionMessage;
		this.timeStamp = timeStamp;
	}


	public String getErrorModule() {
		return errorModule;
	}


	public void setErrorModule(String errorModule) {
		this.errorModule = errorModule;
	}


	public BaseException(HttpStatus httpStatus, String errorCode, String exceptionMessage, String timeStamp) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
		this.timeStamp = timeStamp;
	}
	
	
	public BaseException(HttpStatus httpStatus, String errorCode, String exceptionMessage) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
	}


	public BaseException(HttpStatus httpStatus, String errorCode) {
		super();
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
	}


	public BaseException(String errorCode, String exceptionMessage) {
		super();
		
		this.errorCode = errorCode;
		this.exceptionMessage = exceptionMessage;
	}






	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	

	

}
