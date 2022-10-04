package com.example.hello.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping(value="/", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value="/")
//    @PostMapping(value="/")
    public String sayHello() {
        return "hello";
    }
}
