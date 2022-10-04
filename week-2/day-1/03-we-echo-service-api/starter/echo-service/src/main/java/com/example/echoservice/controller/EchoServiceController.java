package com.example.echoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoServiceController {

    @RequestMapping(value = "/echo/{input}", method = RequestMethod.GET)
    public String echo(@PathVariable String input) {
        return input;
    }
}
