package com.trilogyed.trainreservation.controller;

import com.trilogyed.trainreservation.model.Customer;
import com.trilogyed.trainreservation.service.TrainReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    TrainReservationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        customer = service.createCustomer(customer);
        return customer;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("id") long customerId) {
        Customer customer = service.getCustomer(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer could not be retrieved for id " + customerId);
        } else {
            return customer;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody @Valid Customer customer) {

        if (customer==null || customer.getId()< 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (customer.getId() > 0) {
            service.updateCustomer(customer);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") long customerId) {
        service.deleteCustomer(customerId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        List<Customer> customers = service.getAllCustomers();
        if (customers == null || customers.isEmpty()) {
            throw new IllegalArgumentException("No customers were found");
        } else
            return customers;
    }
}
