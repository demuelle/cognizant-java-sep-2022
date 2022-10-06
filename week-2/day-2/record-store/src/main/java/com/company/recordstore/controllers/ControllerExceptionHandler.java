package com.company.recordstore.controllers;

import com.company.recordstore.models.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    // Created as a part of the record store app on OCtober 6, 2022 in class
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<List<CustomErrorResponse>> handleValidationFailures(MethodArgumentNotValidException ex) {
        // BindingResult holds the validation errors
        BindingResult result = ex.getBindingResult();
        // Validation errors are stored in FieldError objects
        List<FieldError> fieldErrors = result.getFieldErrors();

        // Translate the FieldErrors to CustomErrorResponse
        List<CustomErrorResponse> errorResponseList = new ArrayList<>();

        HttpStatus responseStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        for (FieldError fieldError : fieldErrors) {
            CustomErrorResponse errorResponse = new CustomErrorResponse(responseStatus, fieldError.getDefaultMessage());
            errorResponseList.add(errorResponse);
        }

        // Create and return the ResponseEntity
        ResponseEntity<List<CustomErrorResponse>> responseEntity = new ResponseEntity<>(errorResponseList, responseStatus);
        return responseEntity;
    }


}
