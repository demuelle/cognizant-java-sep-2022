package com.example.echorange.controller;

import com.example.echorange.exceptions.ArgumentIsNotANumberException;
import com.example.echorange.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<CustomErrorResponse> handleOutOfRangeException(IllegalArgumentException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, returnHttpStatus);

        return returnVal;
    }

    @ExceptionHandler(value = {ArgumentIsNotANumberException.class})
    public ResponseEntity<CustomErrorResponse> handleNotANumberException(ArgumentIsNotANumberException ex) {
        HttpStatus returnHttpStatus = HttpStatus.I_AM_A_TEAPOT;
        CustomErrorResponse error = new CustomErrorResponse(returnHttpStatus, ex.getMessage());
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, returnHttpStatus);

        return returnVal;
    }
}
