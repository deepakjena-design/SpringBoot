package com.mindtree.shoppingcart.exception;

import java.util.Date;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final String PLEASE_TRY_AFTER_SOME_TIME = "Some Problem Occured. Please Try After Sometime";
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<ExceptionDetails> handleNoService(RuntimeException eX,WebRequest req){
		logger.error(eX);
		ExceptionDetails details = new ExceptionDetails(new Date(), PLEASE_TRY_AFTER_SOME_TIME);
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value={ResourceNotFoundException.class})
	public ResponseEntity<ExceptionDetails> handleServerError(ResourceNotFoundException eX,WebRequest req){
		logger.error(eX);
		ExceptionDetails details = new ExceptionDetails(new Date(), eX.getMessage(),eX.getMessage());
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= {ServiceException.class})
	public ResponseEntity<ExceptionDetails> handleServiceException(RuntimeException eX,WebRequest req){
		logger.error(eX);
		ExceptionDetails details = new ExceptionDetails(new Date(), PLEASE_TRY_AFTER_SOME_TIME);
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value= {ConstraintViolationException.class})
    public ResponseEntity<ExceptionDetails> invalidParamsExceptionHandler(ConstraintViolationException eX){
		logger.error(eX);
		String exceptionResponse = String.format("Invalid input parameters: %s\n", eX.getMessage());
		ExceptionDetails details = new ExceptionDetails(new Date(), exceptionResponse,eX.getMessage());
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	
    }
	
	
	
}
