package com.office.hours.exceptions;

public class FileTooLargeException extends RuntimeException {
    public FileTooLargeException() {super();}
    public FileTooLargeException(String message) {super(message);}
}
