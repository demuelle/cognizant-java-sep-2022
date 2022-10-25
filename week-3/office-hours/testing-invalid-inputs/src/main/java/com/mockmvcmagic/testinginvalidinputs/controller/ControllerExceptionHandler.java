package com.mockmvcmagic.testinginvalidinputs.controller;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value={HttpMessageNotReadableException.class})
    ResponseEntity<CustomErrorClass> handleMessageNotReadable(HttpMessageNotReadableException ex) {

    }
}
