package com.trilogyed.trainreservation.repository;

import com.trilogyed.trainreservation.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainRepositoryTest {

    @Autowired
    CustomerRepository customerRepo;

    @Before
    public void setUp() {
        customerRepo.deleteAll();
    }

    @Test
    public void shouldSaveCustomer() {
        // Arrange
        // need to set up a customer
        Customer customer = new Customer();
        customer.setFirstName("Gerald");
        customer.setLastName("Ford");
        customer.setEmail("gford@pres.gov");
        customer.setMobile("9988776655");
        customer.setPassword("appointed");

        // put the customer in the database
        // NOTE! After this call, customer will have its id set properly (no need to check the return value)
        // The assignment (customer =) is redundant, but more readable.
        customer = customerRepo.save(customer);

        Customer fromRepo = customerRepo.findById(customer.getId()).get();

        // Act
        // query for customer
        assertEquals(customer,fromRepo);
    }
}