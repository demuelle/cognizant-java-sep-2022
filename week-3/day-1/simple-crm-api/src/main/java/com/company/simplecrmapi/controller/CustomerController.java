package com.company.simplecrmapi.controller;

import com.company.simplecrmapi.dto.Customer;
import com.company.simplecrmapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createNewCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

}
