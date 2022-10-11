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
    public List<Customer> getAllCustomers(@RequestParam(required = false) String company, @RequestParam(required = false) String lastName) {
        if (company != null && lastName != null) {
            return customerRepository.findByCompanyAndLastName(company, lastName);
        } else if (company != null) {
            return customerRepository.findByCompany(company);
        }
        return customerRepository.findAll();
    }

    @GetMapping("/customer/company/{company}")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomersByCompany(@PathVariable String company) {
        return customerRepository.findByCompany(company);
    }


    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createNewCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

}
