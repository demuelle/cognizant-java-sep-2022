package com.trilogyed.hellokube.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloKubeServiceController {

    @GetMapping(value = "/")
    public String hello(){
        return "Hello from minikube!";
    }
}
