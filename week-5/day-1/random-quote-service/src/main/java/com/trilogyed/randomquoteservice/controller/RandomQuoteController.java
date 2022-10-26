package com.trilogyed.randomquoteservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RandomQuoteController {

    Random random = new Random();

    @Autowired
    private DiscoveryClient discoveryClient;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${answerServiceName}")
    private String answerServiceName;

    @Value("${serviceProtocol}")
    private String serviceProtocol;

    @Value("${servicePath}")
    private String servicePath;

    List<String> quoteList;
    public RandomQuoteController() {
        quoteList = new ArrayList<>();
        quoteList.add("To me programming is more than an important practical art. It is also a gigantic undertaking in the foundations of knowledge. —Grace Hopper");
        quoteList.add("Programs must be written for people to read, and only incidentally for machines to execute. —Hal Ableson");
        quoteList.add("Don't call me the mother of the internet. —Radia Perlman");
        quoteList.add("When I first started using the phrase software engineering, it was considered to be quite amusing. They used to kid me about my radical ideas. Software eventually and necessarily gained the same respect as any other discipline. —Margaret Hamilton");
        quoteList.add("Machines take me by surprise with great frequency. —Alan Turing");
        quoteList.add("The function of good software is to make the complex appear simple. —Grady Booch");
        quoteList.add("An API that isn't comprehensible isn't usable. —James Gosling");
        quoteList.add("I'm not a great programmer; I'm just a good programmer with great habits. —Martin Fowler");
    }

    @RequestMapping(value="/answerme", method = RequestMethod.GET)
    public String helloCloud() {

        List<ServiceInstance> instances = discoveryClient.getInstances(answerServiceName);

        String quoteServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;

        String magicEightBallAnswer = restTemplate.getForObject(quoteServiceUri, String.class);

        String returnVal = "The Magic Eight Ball says " + magicEightBallAnswer;
        return returnVal;
    }
    @GetMapping(value="/quote")
    public String getQuote() {
        Random random = new Random();
        return quoteList.get(random.nextInt(quoteList.size()));
    }

    @PostMapping(value="/quote")
    public String addQuote(@RequestBody String newQuote) {
        quoteList.add(newQuote);
        return newQuote;
    }

}
