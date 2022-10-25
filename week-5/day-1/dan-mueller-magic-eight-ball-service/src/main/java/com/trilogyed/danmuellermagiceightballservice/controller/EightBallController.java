package com.trilogyed.danmuellermagiceightballservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class EightBallController {
    Random random = new Random();

    @GetMapping(value="/answer")
    public String getQuote() {
        List<String> answerList = new ArrayList<>();
        answerList.add("No");
        answerList.add("Yes");
        answerList.add("Looking cloudy");
        answerList.add("Not sure");
        answerList.add("Absolutely!");
        answerList.add("Ask again");
        answerList.add("Ummm");
        answerList.add("Not a chance");
        return answerList.get(random.nextInt(answerList.size()));
    }
}
