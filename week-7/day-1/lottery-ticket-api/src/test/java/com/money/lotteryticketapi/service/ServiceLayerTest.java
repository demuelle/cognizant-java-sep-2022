package com.money.lotteryticketapi.service;

import com.money.lotteryticketapi.model.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class ServiceLayerTest {

    private ServiceLayer serviceLayer;

    @Before
    public void setUp() {
        serviceLayer = new ServiceLayer();
    }

    @Test
    public void shouldGenerateValidPowerballTicketMainNumbers() {
        int balls = 5;
        int maxBall = 69;
        int minBall = 1;

        int iterations = 100;
        //        int maxPowerball = 26;

        // because this is random and in-memory, run it 100 times.
        for (int i = 0; i < iterations; i++) {
            Ticket ticket = serviceLayer.generateTicket(balls, maxBall, true, 26);
            List<Integer> numbers = ticket.parseNumbers();
            // convert the list to a set to ensure uniqueness
            Set<Integer> numberSet = new HashSet<>(numbers);

            assertEquals(balls, numberSet.size());
            assertTrue(Collections.min(numbers) >= minBall);
            assertTrue(Collections.max(numbers) <= maxBall);
        }
    }

    @Test
    public void shouldGenerateTicketWithThreeNumbers() {
        int balls = 3;
        int maxBall = 3;
        int minBall = 1;

        int iterations = 100;
        //        int maxPowerball = 26;

        // because this is random and in-memory, run it 100 times.
        for (int i = 0; i < iterations; i++) {
            Ticket ticket = serviceLayer.generateTicket(balls, maxBall, true, 26);
            List<Integer> numbers = ticket.parseNumbers();
            // convert the list to a set to ensure uniqueness
            Set<Integer> numberSet = new HashSet<>(numbers);

            assertEquals(balls, numberSet.size());
            assertTrue(Collections.min(numbers) >= minBall);
            assertTrue(Collections.max(numbers) <= maxBall);
        }
    }


    @Test
    public void shouldGenerateTicketWithValidBonusBall() {
        int balls = 3;
        int maxBall = 3;
        int minBall = 1;
        int bonusBall = 26;

        int iterations = 1000;

        // because this is random and in-memory, run it 100 times.
        for (int i = 0; i < iterations; i++) {
            Ticket ticket = serviceLayer.generateTicket(balls, maxBall, true, bonusBall);

            assertTrue(ticket.getExtraBallNumber() >= 1);
            assertTrue(ticket.getExtraBallNumber() <= bonusBall);
        }
    }


    @Test
    public void shouldGenerateTicketWithBonusBallContainingOnlyPossibleValue() {
        int balls = 3;
        int maxBall = 3;
        int minBall = 1;
        int bonusBall = 1;

        int iterations = 1000;

        // because this is random and in-memory, run it 100 times.
        for (int i = 0; i < iterations; i++) {
            Ticket ticket = serviceLayer.generateTicket(balls, maxBall, true, bonusBall);

            assertEquals(1, ticket.getExtraBallNumber().intValue());
        }
    }


    @Test
    public void bonusBallShouldBeNullWhenNotRequired() {
        int balls = 3;
        int maxBall = 3;
        int minBall = 1;
        int bonusBall = 1;

        int iterations = 1000;

        // because this is random and in-memory, run it 100 times.
        for (int i = 0; i < iterations; i++) {
            Ticket ticket = serviceLayer.generateTicket(balls, maxBall, false, bonusBall);

            assertNull(ticket.getExtraBallNumber());
        }
    }

    @Test
    public void ticketNumbersShouldBeValidIfItMeetAllCriteria() {
        Ticket ticketToTest = new Ticket("1-2-3", null, null);
        int numbersPerTicket = 3;
        int maxBall =3;

        boolean actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertTrue(actualResult);
    }

    @Test
    public void ticketNumbersShouldBeInvalidIfThereAreTooManyNumbers() {
        Ticket ticketToTest = new Ticket("1-2-3-4", null, null);
        int numbersPerTicket = 3;
        int maxBall =3;

        boolean actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);
    }


    @Test
    public void ticketNumbersShouldBeInvalidIfTheNumbersAreOutOfBounds() {
        Ticket ticketToTest = new Ticket("1-2-3-4", null, null);
        int numbersPerTicket =4;
        int maxBall = 3;

        boolean actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);
    }



    @Test
    public void ticketNumbersShouldBeInvalidIfTheInputIsMalformed() {
        Ticket ticketToTest = new Ticket("1--2-3-4", null, null);
        int numbersPerTicket =4;
        int maxBall = 5;

        boolean actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);

        ticketToTest.setNumbers("-1-2-3-4");
        actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);

        ticketToTest.setNumbers("1-2-3-4-");
        actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);

        ticketToTest.setNumbers("1.1-2-3-4");
        actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);


        ticketToTest.setNumbers("1-2-three-4");
        actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);

        ticketToTest.setNumbers("kdjfdkfjdkfjdkjf");
        actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);
    }


    @Test
    public void ticketNumbersShouldBeInvalidIfThereAreNonUniqueInputs() {
        Ticket ticketToTest = new Ticket("1-2-3-1", null, null);
        int numbersPerTicket =4;
        int maxBall = 5;

        boolean actualResult = serviceLayer.validateTicketNumbers(ticketToTest, numbersPerTicket, maxBall);
        assertFalse(actualResult);
    }

    @Test
    public void extraBallNumberShouldBeValid() {
        Ticket ticketToTest = new Ticket();
        ticketToTest.setExtraBallNumber(20);
        int maxBonusBallNumber =26;

        boolean actualResult = serviceLayer.validateExtraBallNumber(ticketToTest, maxBonusBallNumber);
        assertTrue(actualResult);

        ticketToTest.setExtraBallNumber(1);
        actualResult = serviceLayer.validateExtraBallNumber(ticketToTest, maxBonusBallNumber);
        assertTrue(actualResult);


        ticketToTest.setExtraBallNumber(26);
        actualResult = serviceLayer.validateExtraBallNumber(ticketToTest, maxBonusBallNumber);
        assertTrue(actualResult);
    }


}