package com.challenging;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class ArrayUtilsTest {
    private ArrayUtils arrayUtils;

    @Before
    public void setUp() {
        arrayUtils = new ArrayUtils();
    }

    @Test
    public void shouldSumAllArrays() {
        // Arrange
        int expectedValue = 16;
        int[] array1 = {1, 3};
        int[] array2 = {2, 4, 6};

        // Act
        int actualValue = arrayUtils.sumArrays(array1, array2);

        // Assert
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void shouldArrayify() {
        // Arrange
        int[] expectedOutput = {5, 6, 7};

        // Act
        int[] actualOutput = arrayUtils.arrayify(3, 5);

        // Assert
        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void shouldArrayifyWithSizeZero() {
        // Arrange
        int[] expectedOutput = {};

        // Act
        int[] actualOutput = arrayUtils.arrayify(0, 5);

        // Assert
        assertArrayEquals(expectedOutput, actualOutput);
    }
}