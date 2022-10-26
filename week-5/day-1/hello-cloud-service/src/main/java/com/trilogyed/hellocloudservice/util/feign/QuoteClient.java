package com.trilogyed.hellocloudservice.util.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "random-quote-service")
public interface QuoteClient {

    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    public String genericMethodNameForMyAPp();

    @RequestMapping(value="/quote", method=RequestMethod.POST)
    public String addAQuoteToTheQuotesAPI(@RequestBody String theQuoteToAdd);

    @GetMapping(value="/quote")
    public String getQuote();
//    @PutMapping(value="/album/{albumId}")
//    public Album updateAQuote(@PathVariable int albumId, @RequestBody Album updatedQuote);
}