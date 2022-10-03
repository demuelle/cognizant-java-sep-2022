package com.lg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TVTest {

    private TV tv;

    @Before
    public void theVeryBeginningOfTheTestCycle() {
        tv = new TV();
    }

    @Test
    public void shouldTurnOn() {
        // Arrange
        boolean expected = true;

        // Act
        tv.off();
        tv.on();
        boolean actual = tv.isPowerOn();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTurnOff() {
        // Arrange
        boolean expected = false;

        // Act
        tv.on();
        tv.off();

        boolean actual = tv.isPowerOn();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void tvShouldStartPoweredOffOnChannel1() {
        // Arrange and Act
        boolean expectedPower = false;
        int expectedChannel = 1;

        boolean actualPower = tv.isPowerOn();
        int actualChannel = tv.getChannel();

        assertEquals(expectedChannel, actualChannel);
        assertEquals(expectedPower, actualPower);
    }

    @Test
    public void shouldIncreaseTheChannel() {
        // Arrange
        int expectedChannel, actualChannel;

        expectedChannel = 2;

        // Act
        tv.increaseChannel();
        actualChannel = tv.getChannel();

        // Assert
        assertEquals(expectedChannel, actualChannel);

        // Arrange
        expectedChannel = 3;

        // Act
        tv.increaseChannel();
        actualChannel = tv.getChannel();

        // Assert
        assertEquals(expectedChannel, actualChannel);

    }

}


// sumArrays([],[]) returns the sum of all the numbers!
// sumArrays([1,2],[1,5]) returns 9 because 1 + 2 + 1 = 5 is 9
// sumArrays([3], [4, 3, 7, 10, 11]) returns 3+4+3+7+10+11=38

// sumArray([1, 2, 3]) returns sum of the numbers in the array (1 + 2 + 3 = 6)
// sumArray([]) = 0
