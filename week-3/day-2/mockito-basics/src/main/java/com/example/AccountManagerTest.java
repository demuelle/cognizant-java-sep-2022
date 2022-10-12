package com.example;

import org.junit.Test;

import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class AccountManagerTest {

    @Test
    public void withoutMocks() {

        SimpleFormatChecker simpleFormatChecker = new SimpleFormatChecker();
        SimpleAccountRepository simpleAccountRepository = new SimpleAccountRepository();

        AccountManager accountManager = new AccountManager(simpleFormatChecker, simpleAccountRepository);

        assertTrue(accountManager.CreateNew("username", "passw0rd"));
        assertTrue(accountManager.CreateNew("username", "passw0rd"));
    }

    @Test
    public void withMockedObjects() {
        FormatChecker mockedFormatChecker = mock(FormatChecker.class);
        doReturn(true).when(mockedFormatChecker).ValidateUsername("username");
        doReturn(true).when(mockedFormatChecker).ValidateUsername("danimalthemanimal");
        doReturn(true).when(mockedFormatChecker).ValidatePassword("passw0rd");
        doReturn(true).when(mockedFormatChecker).ValidatePassword("supsersecetshhhh1");

        // alternate
        when(mockedFormatChecker.ValidatePassword("passw0rd")).thenReturn(true);

        // mocking a database
        /*
        doReturn(new Lion(1, "Simba", 350)).when(lionDbRepo).save(Lion("Simba", 350));
        doReturn(new Lion(1, "Simba", 350)).when(lionDbRepo).findById(1);

        lionDbRepo.findById(1)


         */

        AccountRepository mockedAccountRepository = mock(AccountRepository.class);
        doReturn(true).when(mockedAccountRepository).CreateAccount("username", "passw0rd");
        doReturn(true).when(mockedAccountRepository).CreateAccount("danimalthemanimal", "supsersecetshhhh1");

        AccountManager accountManager = new AccountManager(mockedFormatChecker, mockedAccountRepository);
        assertTrue(accountManager.CreateNew("username", "passw0rd"));
        assertTrue(accountManager.CreateNew("username", "passw0rd"));
        assertTrue(accountManager.CreateNew("danimalthemanimal", "supsersecetshhhh1"));


    }

    @Test
    public void withSpies() {
        FormatChecker spyFormatChecker = spy(SimpleFormatChecker.class);
        AccountRepository spyAccountRepository = spy(SimpleAccountRepository.class);

        AccountManager accountManager = new AccountManager(spyFormatChecker, spyAccountRepository);
        accountManager.CreateNew("username", "passw0rd");
        accountManager.CreateNew("username2", "passw0rd2");

        verify(spyFormatChecker).ValidateUsername("username");
        verify(spyFormatChecker).ValidatePassword("passw0rd2");

        verify(spyFormatChecker, times(2)).ValidateUsername(anyString());
    }
}