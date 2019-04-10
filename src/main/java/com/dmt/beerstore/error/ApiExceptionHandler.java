package com.dmt.beerstore.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dmt.beerstore.error.ErrorResponse.ApiError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler {

	private static final String MSG_ERROR = "NO MESSSAGE AVALIBLE";

	private final MessageSource apiErrorMessageSource;

	protected ApiError createApiError(String code, Locale locale, Object... args) {
		String message;
		try {
			message = apiErrorMessageSource.getMessage(code, args, locale);
		} catch (NoSuchMessageException e) {
			log.error("Could not find any message for {} code under {} locale", code, locale);
			message = MSG_ERROR;
		}

		return new ApiError(code, message);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	private ResponseEntity<ErrorResponse> handlerNotValidException(MethodArgumentNotValidException exception,
			Locale locale) {

		List<ObjectError> listObjectError = exception.getBindingResult().getAllErrors();
		List<ApiError> listErros = new ArrayList<ApiError>();
		for (ObjectError objectError : listObjectError) {
			listErros.add(createApiError(objectError.getDefaultMessage(), locale));
		}

		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, listErros);
		return ResponseEntity.badRequest().body(errorResponse);

	}

	@ExceptionHandler(InvalidFormatException.class)
	private ResponseEntity<ErrorResponse> handlerInvalidFormatException(InvalidFormatException exception,
			Locale locale) {

		List<ApiError> listErros = new ArrayList<ApiError>();

		listErros.add(createApiError("generic-1", locale, exception.getValue()));

		ErrorResponse errorResponse = ErrorResponse.of(HttpStatus.BAD_REQUEST, listErros);
		return ResponseEntity.badRequest().body(errorResponse);

	}

}
