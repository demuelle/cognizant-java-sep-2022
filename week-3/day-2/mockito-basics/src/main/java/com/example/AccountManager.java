package com.example;

public class AccountManager
{
    private FormatChecker formatChecker;
    private AccountRepository accountRepository;

    public AccountManager(FormatChecker formatChecker, AccountRepository accountRepository) {
        this.formatChecker = formatChecker;
        this.accountRepository = accountRepository;
    }

    public boolean CreateNew(String username, String password) {
        if (formatChecker.ValidateUsername(username) && formatChecker.ValidatePassword(password)) {
            return accountRepository.CreateAccount(username, password);
        } else {
            return false;
        }
    }
}
