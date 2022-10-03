package edu.smu;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnotherCalculatorTest {
    private AnotherCalculator calculator;

    @Before
    public void setUp() {
        calculator = new AnotherCalculator();
    }

    @Test
    public void shouldDivideIntegers() {
        // Arrange
        int expectedValue = 5;

        // Act
        int actualValue = calculator.divide(5, 1);

        // Assert
        assertEquals(expectedValue, actualValue);
        assertEquals(-3, calculator.divide(-9, 3));
        assertEquals(0, calculator.divide(0, 133));
        assertEquals(18, calculator.divide(-54, -3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldGracefullyHandleDivideByZero() {
        // Act
        int actualValue = calculator.divide(5, 0);

        // Assert
    }

}