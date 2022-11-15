package com.money.lotteryticketapi.service;

import com.money.lotteryticketapi.model.Ticket;

import java.util.*;

public class ServiceLayer {
    public Ticket generateTicket(int regularNumbersPerTicket, int maxBall, boolean bonusBall, int maxBonusBall) {
        // TODO: build ticket generation rules functionality
        //       For example, have a method that returns a Rules object with total numbers possible,
        //       numbers per ticket, if there's a bonus ball, and if there's a multiplier ball

        Ticket returnVal = new Ticket();
        // if we want to allow numbers to repeat, we should declare a collection, and then use a
        //   TreeSet for non-repeating numbers (should be sorted)
        //   And a List for repeating numbers. In these lottery games, the numbers are not generally sorted
        //     for example, 3-3-4 might win a prize, but if I had 3-4-3, I might not win
        Set<Integer> numbers = new TreeSet<>();

        Random randomNumberGenerator = new Random();
        while(numbers.size() < regularNumbersPerTicket) {
            numbers.add(randomNumberGenerator.nextInt(maxBall) + 1);
        }

        returnVal.storeNumbers(new ArrayList<>(numbers));

        if (bonusBall) {
            returnVal.setExtraBallNumber(randomNumberGenerator.nextInt(maxBonusBall) + 1);
        }

        return returnVal;
    }

    /**
     * validateTicketNumbers - return true if
     *                              the numbers field of the ticket is a hyphen delimited list
     *                              containing exactly the number of numbers described by regularNumbersPerTicket
     *                              and with all numbers unique and between 1 and maxBall
     *                         otherwise return false
     * @param ticket - contains numbers to validate
     * @param regularNumbersPerTicket - the number of unique numbers on the ticket
     * @param maxBall - the maximum value for a ball
     * @return boolean
     */
    public boolean validateTicketNumbers(Ticket ticket, int regularNumbersPerTicket, int maxBall) {
        // could be solved with a regex
        String[] numberStrings = ticket.getNumbers().split("-");
        if (numberStrings.length != regularNumbersPerTicket) {
            System.err.println("Invalid ticket: wrong number of fields: \'" + ticket.getNumbers() + "\'");
            return false;
        }

        Set<Integer> uniqueNumbers = new TreeSet<>();
        // check that each number is in the proper range, and doesn't have leading zeroes or decimal points
        for (String numberString : numberStrings) {
            int number = -1;
            try {
                number = Integer.parseInt(numberString);
            } catch (NumberFormatException ex) {
                System.err.println("Invalid ticket: contains invalid number: \'" + numberString + "\'");
                return false;
            }
            if (number <= 0 || number > maxBall) {
                System.err.println("Invalid ticket: contains out of range number: \'" + numberString + "\'. Numbers " +
                        " must be between 1 and " + maxBall);
                return false;
            }
            uniqueNumbers.add(number);
        }

        if (uniqueNumbers.size() != regularNumbersPerTicket) {
            System.err.println("Invalid ticket: wrong number of unique numbers: \'" + ticket.getNumbers() + "\'");
            return false;
        }
        String originalNumbers = ticket.getNumbers();
        ticket.storeNumbers(new ArrayList<>(uniqueNumbers));
        if (originalNumbers.length() != ticket.getNumbers().length()) {
            System.err.println("Probable formatting error on ticket numbers input: \'" + originalNumbers + "\'");
            return false;
        }

        return true;
    }

    public boolean validateExtraBallNumber(Ticket ticket, int maxBonusBallNumber) {
        if (ticket.getExtraBallNumber() == null) {
            System.err.println("Extra ball is null, but required");
        } else if (ticket.getExtraBallNumber() <= 0
                || ticket.getExtraBallNumber() > maxBonusBallNumber) {
            System.err.println("Extra ball out of range. Must be between 1 and " + maxBonusBallNumber + ". Provided: " + ticket.getExtraBallNumber());
            return false;
        }
        return true;
    }
}
