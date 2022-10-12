package com.example;

import java.util.*;

public class SimpleAccountRepository implements AccountRepository {
    private Set<String> accountList = new HashSet<String>();

    @Override
    public boolean CreateAccount(String username, String password) {
        if (accountList.contains(username)) {
            return false;
        }

        accountList.add(username);
        return true;
    }
}
