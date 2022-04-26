package com.backendtest.myapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.backendtest.myapp.errors.ProductSimilarNotFoundException;

@ControllerAdvice
public class ProductSimilarExceptionController {

    @ExceptionHandler(value = ProductSimilarNotFoundException.class)
    public ResponseEntity<Object> exception(ProductSimilarNotFoundException exception) {
	return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
    }
}
