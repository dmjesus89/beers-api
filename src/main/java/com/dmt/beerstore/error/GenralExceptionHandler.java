package com.dmt.beerstore.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dmt.beerstore.error.ErrorResponse.ApiError;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GenralExceptionHandler {

	@Autowired
	private final ApiExceptionHandler apiExceptionHandler;

	@ExceptionHandler(Exception.class)
	private ResponseEntity<ErrorResponse> handlerException(Exception exception, Locale locale) {

		log.error("Error no expected", exception);

		List<ApiError> listErros = new ArrayList<ApiError>();

		listErros.add(apiExceptionHandler.createApiError("error-1", locale));

		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, listErros);
		return ResponseEntity.badRequest().body(errorResponse);

	}

}
