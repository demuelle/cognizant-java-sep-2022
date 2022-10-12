package com.example;

public class Application {
    public static void main(String[] args) {

        SimpleFormatChecker formatChecker = new SimpleFormatChecker();
        SimpleAccountRepository repository = new SimpleAccountRepository();

        AccountManager accountManager = new AccountManager(formatChecker, repository);

        if (accountManager.CreateNew("blahblah", "abcdefgh")) {
            System.out.println("Successfully created blahblah");
        }

        if (accountManager.CreateNew("blahblah", "12345678")) {
            System.out.println("Successfully created blahblah the second time");
        }

        if (accountManager.CreateNew("username", "password")) {
            System.out.println("Successfully created username");
        }
    }
}
