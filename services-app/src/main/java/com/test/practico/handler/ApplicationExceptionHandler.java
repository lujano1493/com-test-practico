package com.test.practico.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler( MethodArgumentNotValidException.class )
	public ResponseEntity<Map <String,String>> handlerValidationException(MethodArgumentNotValidException ve){
		Map<String,String> errors =  new HashMap<String, String>();
		
		ve.getBindingResult().getAllErrors().forEach(  e ->{
			String fielName = ( (FieldError) e).getField();
			String value =  e.getDefaultMessage();
			errors.put(fielName, value);
			
		}  );
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List <String>> handlerValidationException(ConstraintViolationException ce){
		List<String> errors =  new ArrayList<>();
		
		ce.getConstraintViolations().forEach(  constraint ->{
			String msg = String.format("%s %s %s", constraint.getPropertyPath(), constraint.getInvalidValue(),constraint.getMessage() );
			errors.add(msg);
		});
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	

}
