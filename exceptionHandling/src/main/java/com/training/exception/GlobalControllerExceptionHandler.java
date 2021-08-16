package com.training.exception;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler 
{

	@Autowired
	MessageSourceUtil messageSourceUtil;
	

	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders header ,HttpStatus status,WebRequest request){
		List <String> details= new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		
		return new ResponseEntity<Object>(details, HttpStatus.BAD_REQUEST);

	}
	
	
	
	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Object> handleApplicationException(WebRequest request, Exception ex){
		BaseException e = (BaseException)ex;
		return getCustomExceptionResponse(request,e);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(WebRequest request, Exception ex){
		BaseException e = (BaseException)ex;
		return getCustomExceptionResponse(request,e);
	}
	
	private ResponseEntity<Object> getCustomExceptionResponse(WebRequest request, BaseException ex) {
		String errorCode = ex.getErrorCode();
		String exceptionMessage = ex.getExceptionMessage();
		String timeStamp = ex.getTimeStamp();
		String errorModule = ex.getErrorModule();
		String errorMessage = "";
	
		try{
			errorMessage = messageSourceUtil.getLocalisedText(errorCode, errorModule);
		}catch(Exception e) {
			errorMessage = messageSourceUtil.getLocalisedText("001", "Genral");
			exceptionMessage = "The message for errorCode:"+errorCode+" module:"+errorModule+" is not found in prop file";
		}
		HttpStatus status = ex.getHttpStatus();
		
		return new ResponseEntity<>(new StandrdError( errorMessage,errorCode, null, timeStamp,exceptionMessage), status);
	}

}
