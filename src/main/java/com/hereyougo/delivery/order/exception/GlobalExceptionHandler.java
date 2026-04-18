package com.hereyougo.delivery.order.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException (OrderNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

}
