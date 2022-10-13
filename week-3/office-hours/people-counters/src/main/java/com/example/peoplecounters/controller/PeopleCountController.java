package com.example.peoplecounters.controller;

import com.example.peoplecounters.PeopleCountRepository;
import com.example.peoplecounters.service.CalculationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleCountController {
    @Autowired
    private CalculationsService calculationsService;

}
