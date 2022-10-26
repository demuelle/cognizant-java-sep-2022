package com.trilogyed.hellocloudservice.controller;

import com.trilogyed.hellocloudservice.util.feign.QuoteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RefreshScope
public class HelloCloudServiceController {//    @Autowired
    //    private DiscoveryClient discoveryClient;
    //
    //    private RestTemplate restTemplate = new RestTemplate();
    //
    //    @Value("${quoteServiceName}")
    //    private String quoteServiceName;
    //
    //    @Value("${serviceProtocol}")
    //    private String serviceProtocol;
    //
    //    @Value("${servicePath}")
    //    private String servicePath;
    //
    //    @Value("${officialGreeting}")
    //    private String officialGreeting;

    @Autowired
    private final QuoteClient client;

    HelloCloudServiceController(QuoteClient client) {
        this.client = client;
    }

    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String helloCloud() {

        //        List<ServiceInstance> instances = discoveryClient.getInstances(quoteServiceName);

        //        String quoteServiceUri = serviceProtocol + instances.get(0).getHost() + ":" + instances.get(0).getPort() + servicePath;

        //        String quote = restTemplate.getForObject(quoteServiceUri, String.class);

        //        return quote;

        return client.genericMethodNameForMyAPp();
    }

    @RequestMapping(value="/addaquote", method=RequestMethod.POST)
    public String addAQuote(@RequestBody String newQuoteToAdd) {
        String postResult = client.addAQuoteToTheQuotesAPI(newQuoteToAdd);
        return "Congratulations! You added a quote to the quotes API using a feign client: " + postResult;
    }
}
