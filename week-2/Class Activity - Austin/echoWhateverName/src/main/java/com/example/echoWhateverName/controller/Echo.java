package com.example.echoWhateverName.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Echo {
    @RequestMapping(value="/echo/{input}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String echoReturnString(@PathVariable String input){
      return input;
    }
}
