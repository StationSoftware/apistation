package com.station.api.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.validation.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.station.api.controller.ApiExceptionHandler;
import com.station.api.model.ApiError;
import com.station.api.util.PrepareResponse;

public class ApiExceptionHandlerTest {
	
	@InjectMocks
	private ApiExceptionHandler apiExceptionHandler;
	
	@Mock
	PrepareResponse prepareResponse;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testApiExceptionHandler_handleValidationException() {
		
		ValidationException mockException = new ValidationException("Mock Exception Message");
		List<String> mockErrors = Arrays.asList(mockException.getMessage());
		ApiError mockApiError = new ApiError(HttpStatus.BAD_REQUEST, mockException.getLocalizedMessage(), mockErrors);
		when(prepareResponse.getHttpHeaders())
			.thenReturn(getHttpHeaders());
		
		ResponseEntity<Object> testResponseEntity
		= apiExceptionHandler.handleValidationException(mockException);
		
		verify(prepareResponse).getHttpHeaders();
		assertThat(testResponseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
		assertThat(testResponseEntity.getHeaders(), is(getHttpHeaders()));
		assertThat(((ApiError) testResponseEntity.getBody()).getStatus(), is(mockApiError.getStatus()));
		assertThat(((ApiError) testResponseEntity.getBody()).getMessage(), is(mockApiError.getMessage()));
		assertThat(((ApiError) testResponseEntity.getBody()).getErrors(), is(mockApiError.getErrors()));
	}
	
	private HttpHeaders getHttpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type");
		return httpHeaders;
	}
	
}
