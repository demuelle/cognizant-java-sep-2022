package com.example;

public class SimpleFormatChecker implements FormatChecker {
    @Override
    public boolean ValidateUsername(String username) {
        return username.length() >= 8;
    }

    @Override
    public boolean ValidatePassword(String password) {
        return password.length() >= 8 && !password.equals("password");
    }
}
