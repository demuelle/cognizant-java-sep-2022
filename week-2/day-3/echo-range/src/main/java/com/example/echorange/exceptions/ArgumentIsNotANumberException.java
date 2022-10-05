package com.example.echorange.exceptions;

public class ArgumentIsNotANumberException extends RuntimeException {
    public ArgumentIsNotANumberException() {
    }

    public ArgumentIsNotANumberException(String message) {
        super(message);
    }
}
