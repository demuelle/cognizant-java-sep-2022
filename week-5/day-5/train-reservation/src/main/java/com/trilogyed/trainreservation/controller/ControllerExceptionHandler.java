package com.trilogyed.trainreservation.controller;

import com.trilogyed.trainreservation.model.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<CustomErrorResponse> handleOutOfRangeException(IllegalArgumentException ex) {
        HttpStatus returnHttpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        CustomErrorResponse error = CustomErrorResponse.build(returnHttpStatus, ex.getMessage());
        ResponseEntity<CustomErrorResponse> returnVal = new ResponseEntity<>(error, returnHttpStatus);

        return returnVal;
    }}
