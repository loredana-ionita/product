package com.rakuten.product.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rakuten.product.domain.ResponseError;

@ControllerAdvice
public class ProductExceptionHandler extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9162644373159797110L;

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ResponseError> handleProductNotFoundException(Exception e) {
		ResponseError responseError = new ResponseError(HttpStatus.NOT_FOUND.value(), "NOT_FOUND");
		return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ThirdPartyException.class)
	public ResponseEntity<ResponseError> handleThirdPartyException(Exception e){
		ResponseError responseError = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR");
		return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ResponseError> handleMethodNotSupportes(Exception e) {
		ResponseError responseError = new ResponseError(HttpStatus.METHOD_NOT_ALLOWED.value(), "METHOD_NOT_ALLOWED");
		return new ResponseEntity<>(responseError, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ResponseError> handleBadRequestException(Exception e){
		ResponseError responseError = new ResponseError(HttpStatus.BAD_REQUEST.value(), "BAD_REQUEST");
		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}

}
