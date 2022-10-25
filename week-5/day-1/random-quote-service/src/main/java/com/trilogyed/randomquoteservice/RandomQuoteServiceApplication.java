package com.trilogyed.randomquoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RandomQuoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomQuoteServiceApplication.class, args);
	}

}
