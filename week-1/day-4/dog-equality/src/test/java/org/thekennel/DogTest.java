package org.thekennel;

import org.junit.Test;

import static org.junit.Assert.*;

public class DogTest {

    @Test
    public void haveABirthdayShouldIncreaseTheDogsAgeByOne() {
        // Arrange
        Dog actualDog = new Dog(6, "red", "car");
        Dog expectedDog = new Dog(7, "red", "car");

        // Act
        actualDog.haveABirthday();

        // Assert
        assertEquals(expectedDog, actualDog);

    }

}