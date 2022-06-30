package com.duth.engapp.controller;

import com.duth.engapp.payload.ApiResponse;
import org.hibernate.boot.MappingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.HashMap;
import java.util.Map;

public class RootController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
        ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Something is missing",null );
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        response.setError(errors);
        return response;
    }
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse missRequestParam(MethodArgumentNotValidException ex) {
        return new ApiResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Request param is missing",null );
    }
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(MethodNotAllowedException.class)
    public ApiResponse methodNotSupported(MethodArgumentNotValidException ex) {
        return new ApiResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not supported",null );
    }
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse methodNotSupported(MappingNotFoundException ex) {
        return new ApiResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not allow",null );
    }
}
