package com.kakaopay.api4pj.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Map<String, Object>> notFoundExceptionHandler(NotFoundException e) {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("code", "404");
		responseBody.put("메세지", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(responseBody,HttpStatus.NOT_FOUND);
	}
}
