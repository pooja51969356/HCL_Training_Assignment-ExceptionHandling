package com.training.exception;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler 
{

	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders header ,HttpStatus status,WebRequest request){
		List <String> details= new ArrayList<>();
		ErrorMessage message = new ErrorMessage(
	            new Date(),
	            "Invalid Arguments",
	            request.getDescription(false),details);
		
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyInputException emptyInputException){
		return new ResponseEntity<String>("Input field is empty, please check", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoElementFoundException.class)
	public ResponseEntity<String> handleNoElementFoundException(NoElementFoundException noElementFoundException){
		return new ResponseEntity<String>("Empty list, No Record Found", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException){
		return new ResponseEntity<String>("No Record Found ", HttpStatus.NOT_FOUND);
	}
	
			@ExceptionHandler(MethodArgumentTypeMismatchException.class)
		    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
		        MethodArgumentTypeMismatchException ex,
		        WebRequest request) 
			{
				
				List<String> details = new ArrayList<String>();
		        details.add(ex.getMessage());
				
				ErrorMessage message = new ErrorMessage(
			            new Date(),
			            "Invalid Arguments",
			            request.getDescription(false),details);
			   
			      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		    }
		
			//This exception reports the result of all other exceptions
			@ExceptionHandler(Exception.class)
			public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) 
			{
				List<String> details = new ArrayList<String>();
		        details.add(ex.getLocalizedMessage());
			    ErrorMessage message = new ErrorMessage(
			        new Date(),
			        "Error occurred",
			        request.getDescription(false),details);
			    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
			  }
			
			
}
