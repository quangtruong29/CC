package com.duth.engapp.controller;

import com.duth.engapp.payload.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler{

   @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
   public ApiResponse handlerRequestMethodNotSupported(
           HttpRequestMethodNotSupportedException ex) {
       return new ApiResponse(405, "Request not allow", null);
   }
}