package com.example;

public interface AccountRepository {
    public boolean CreateAccount(String username, String password);
}

// mock implementation
    /*
public boolean CreateAccount(String username, String password) {
    if (username.equals("username") && password.equals("passw0rd")) {
        return true;
    }
    if (username.equals("danimalthemanimal") && password.equals("supsersecetshhhh1")) {
        return true;
    }
    return false;
   }



     */
