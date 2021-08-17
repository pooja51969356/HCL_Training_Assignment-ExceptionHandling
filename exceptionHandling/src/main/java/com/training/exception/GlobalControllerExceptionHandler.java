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
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		
		return new ResponseEntity<Object>(details, HttpStatus.BAD_REQUEST);

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
			
			
				/*
				 * @ExceptionHandler(ResourceNotFoundException.class)
				 * 
				 * @ResponseStatus(value = HttpStatus.NOT_FOUND) public ErrorMessage
				 * resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
				 * ErrorMessage message = new ErrorMessage( HttpStatus.NOT_FOUND.value(), new
				 * Date(), ex.getMessage(), request.getDescription(false));
				 * 
				 * return message; }
				 */
	  
		/*
		 * @ExceptionHandler(Exception.class)
		 * 
		 * @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) public ErrorMessage
		 * globalExceptionHandler(Exception ex, WebRequest request) { ErrorMessage
		 * message = new ErrorMessage( HttpStatus.INTERNAL_SERVER_ERROR.value(), new
		 * Date(), ex.getMessage(), request.getDescription(false));
		 * 
		 * return message; }
		 */
	  
	  
	
		/*
		 * @ExceptionHandler(ApplicationException.class) public ResponseEntity<Object>
		 * handleApplicationException(WebRequest request, Exception ex){ BaseException e
		 * = (BaseException)ex; return getCustomExceptionResponse(request,e); }
		 * 
		 * @ExceptionHandler(BusinessException.class) public ResponseEntity<Object>
		 * handleBusinessException(WebRequest request, Exception ex){ BaseException e =
		 * (BaseException)ex; return getCustomExceptionResponse(request,e); }
		 * 
		 * private ResponseEntity<Object> getCustomExceptionResponse(WebRequest request,
		 * BaseException ex) { String errorCode = ex.getErrorCode(); String
		 * exceptionMessage = ex.getExceptionMessage(); String timeStamp =
		 * ex.getTimeStamp(); String errorModule = ex.getErrorModule(); String
		 * errorMessage = "";
		 * 
		 * try{ errorMessage = messageSourceUtil.getLocalisedText(errorCode,
		 * errorModule); }catch(Exception e) { errorMessage =
		 * messageSourceUtil.getLocalisedText("001", "Genral"); exceptionMessage =
		 * "The message for errorCode:"+errorCode+" module:"
		 * +errorModule+" is not found in prop file"; } HttpStatus status =
		 * ex.getHttpStatus();
		 * 
		 * return new ResponseEntity<>(new StandrdError( errorMessage,errorCode, null,
		 * timeStamp,exceptionMessage), status); }
		 */
}
