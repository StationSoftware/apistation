package com.station.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.station.api.model.ApiError;
import com.station.api.util.PrepareResponse;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	PrepareResponse prepareResponse;

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(
					// violation.getRootBeanClass().getName() + " " +
					violation.getPropertyPath() + ": " + violation.getMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, 
											ex.getLocalizedMessage(), 
											errors);
		return new ResponseEntity<Object>(apiError, 
											prepareResponse.getHttpHeaders(), 
											apiError.getStatus());
	}

	@ExceptionHandler({ ValidationException.class })
	public ResponseEntity<Object> handleValidationException(ValidationException ex) {
		
		List<String> errors = Arrays.asList(ex.getMessage());
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,
											ex.getLocalizedMessage(),
											errors);
		return new ResponseEntity<Object>(apiError, 
											prepareResponse.getHttpHeaders(), 
											apiError.getStatus());
	}

}
