package com.example.demo.config.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception, WebRequest request){
		
		ExceptionDetails errorDatails = new ExceptionDetails(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<ExceptionDetails>(errorDatails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
