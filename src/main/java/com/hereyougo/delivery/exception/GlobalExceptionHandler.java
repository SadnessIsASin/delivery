package com.hereyougo.delivery.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourierNotFoundException.class)
    public ResponseEntity<?> handleCourierNotFoundException (CourierNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleOrderNotFoundException (OrderNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}