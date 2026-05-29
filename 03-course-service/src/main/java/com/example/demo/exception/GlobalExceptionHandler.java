package com.example.demo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setName(HttpStatus.NOT_FOUND.name());
		error.setError(ex.getMessage());
		error.setPath(request.getRequestURI());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	ResponseEntity<ApiError> handleDuplicateResourceException(DuplicateResourceException ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		
		error.setStatus(HttpStatus.CONFLICT.value());
		error.setName(HttpStatus.CONFLICT.name());
		error.setError(ex.getMessage());
		error.setPath(request.getRequestURI());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ApiError> handleGenericException(Exception ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setName(HttpStatus.INTERNAL_SERVER_ERROR.name());
		error.setError("Unexpected error occured.");
		error.setPath(request.getRequestURI());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request)
	{
		ApiError error = new ApiError();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setName(HttpStatus.BAD_REQUEST.name());
		error.setError(ex
				.getBindingResult()
				.getFieldError()
				.getDefaultMessage());
		error.setPath(request.getRequestURI());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
