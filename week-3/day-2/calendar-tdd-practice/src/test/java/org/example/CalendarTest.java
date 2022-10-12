package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarTest {

    private Calendar calendar;

    @Before
    public void setUp() throws Exception {
        calendar = new Calendar();
    }

    @Test
    public void januaryShouldAlwaysHave31Days() {
        // Arrange
        int expectedValue = 31;
        // Act
        int actualValue = calendar.calculateDaysInMonth(1, 2022);

        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void aprilShouldAlwaysHave30Days() {
        // Arrange
        int expectedValue = 30;
        // Act
        int actualValue = calendar.calculateDaysInMonth(4, 2022);
        // Assert
        assertEquals(expectedValue, actualValue);

        assertEquals(30, calendar.calculateDaysInMonth(4, 2022));
        assertEquals(30, calendar.calculateDaysInMonth(4, 2023));
        assertEquals(30, calendar.calculateDaysInMonth(4, 2024));
        assertEquals(30, calendar.calculateDaysInMonth(4, 1900));
        assertEquals(30, calendar.calculateDaysInMonth(4, 2000));
    }

    @Test
    public void februaryShouldHave28DaysWhenYearIsNotAMultipleOf4() {
        // Arrange
        int expectedValue = 28;
        // Act
        int actualValue = calendar.calculateDaysInMonth(2, 2022);
        // Assert
        assertEquals(expectedValue, actualValue);
    }


    @Test
    public void februaryShouldHave29DaysWhenYearIsALeapYear() {
        // Arrange
        int expectedValue = 29;
        // Act
        int actualValue = calendar.calculateDaysInMonth(2, 2024);
        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void februaryShouldHave28DaysWhenYearIsMultipleOf100ButNotAMultipleOf400() {
        // Arrange
        int expectedValue = 28;
        // Act
        int actualValue = calendar.calculateDaysInMonth(2, 1900);
        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void februaryShouldHave29DaysWhenYearIsMultipleOf400() {
        // Arrange
        int expectedValue = 29;
        // Act
        int actualValue = calendar.calculateDaysInMonth(2, 2000);
        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void badMonthNumberShouldThrowIllegalArgumentException() {
        // Act
        int actualValue = calendar.calculateDaysInMonth(-874, 1900);
    }

}