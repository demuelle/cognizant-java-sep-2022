package com.example;

public interface FormatChecker {
    public boolean ValidateUsername(String username);
    public boolean ValidatePassword(String password);
}

// mock implementation below!
/*
boolean ValidateUsername(String username) {
    if (username.equals("username")) {
       return true;
    }
    return false; }


    boolean ValidatePassword(String username) { return false; }


 */