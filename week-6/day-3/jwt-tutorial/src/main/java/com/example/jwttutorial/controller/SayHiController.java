package com.example.jwttutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHiController {

    @GetMapping("/say_hi_public")
    public String sayHiPublic() {
        return "PUBLIC: Hello!!!!";
    }

    @GetMapping("/say_hi_private")
    public String sayHiPrivate() {
        return "**PRIVATE**: Hiya!!!!";
    }
}
