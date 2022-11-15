package com.money.lotteryticketapi.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TicketTest {

    @Test
    public void shouldParseNumbers() {
        Ticket ticket = new Ticket("1-2-4", 3, true);
        List<Integer> expectedValue = Arrays.asList(1, 2, 4);

        List<Integer> actualOutput = ticket.parseNumbers();
        assertEquals(expectedValue, actualOutput);
    }
    @Test
    public void parseNumbersShouldReturnEmptyListWhenNumbersIsNull() {
        Ticket ticket = new Ticket(null, 3, true);
        List<Integer> expectedValue = new ArrayList<>();

        List<Integer> actualOutput = ticket.parseNumbers();
        assertEquals(expectedValue, actualOutput);
    }

    @Test
    public void parseNumbersShouldReturnListWithOneItemIfNumbersHasOnlyOneNumber() {
        Ticket ticket = new Ticket("452", 3, true);
        List<Integer> expectedValue = Arrays.asList(452);

        List<Integer> actualOutput = ticket.parseNumbers();
        assertEquals(expectedValue, actualOutput);
    }

    @Test
    public void shouldStoreListOfNumbersAsHyphenDelimitedString() {
        // Arrange
        Ticket ticket = new Ticket();
        List<Integer> valuesToStore = Arrays.asList(18,55,32,454,9,100,6,12,2);
        String expectedValue = "18-55-32-454-9-100-6-12-2";

        // Act
        ticket.storeNumbers(valuesToStore);
        String actualValue = ticket.getNumbers();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldStoreSingleNumberAsString() {
        Ticket ticket = new Ticket();
        List<Integer> valuesToStore = Arrays.asList(3);
        String expectedValue = "3";

        // Act
        ticket.storeNumbers(valuesToStore);
        String actualValue = ticket.getNumbers();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldHandleEmptyInputToStoreNumbers() {
        Ticket ticket = new Ticket();
        List<Integer> valuesToStore = new ArrayList<>();
        String expectedValue = "";

        // Act
        ticket.storeNumbers(valuesToStore);
        String actualValue = ticket.getNumbers();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldHandleNullInputToStoreNumbers() {
        Ticket ticket = new Ticket();
        List<Integer> valuesToStore = null;
        String expectedValue = "";

        // Act
        ticket.storeNumbers(valuesToStore);
        String actualValue = ticket.getNumbers();

        assertEquals(expectedValue, actualValue);
    }

}