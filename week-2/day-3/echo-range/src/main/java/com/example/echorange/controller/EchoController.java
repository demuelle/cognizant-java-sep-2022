package com.example.echorange.controller;

import com.example.echorange.exceptions.ArgumentIsNotANumberException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    // Elleni said this first. Then, Austin typed it.
    @GetMapping(value = "/echo/{input}")
    @ResponseStatus(HttpStatus.OK)
    public int echo(@PathVariable String input) {
        int inputInt = 0;
        try {
            inputInt = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input provided to echo endpoint");
            throw new ArgumentIsNotANumberException("Expected an int, but got " + input);
        }

        if (inputInt < 1 || inputInt > 10) {
            throw new IllegalArgumentException("Input must be between 1 and 10.");
        }
        return inputInt;
    }

}
