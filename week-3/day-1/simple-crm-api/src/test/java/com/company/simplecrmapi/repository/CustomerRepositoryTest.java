package com.company.simplecrmapi.repository;

import com.company.simplecrmapi.dto.Customer;
import com.company.simplecrmapi.dto.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Before
    public void setUp() {
        noteRepository.deleteAll();
        customerRepository.deleteAll();
    }

    @Test
    public void checkDatabaseIntegration() {
        Customer customer = new Customer();
        customer.setFirstName("Jonathan");
        customer.setLastName("J'lastName");
        customer.setPhone("333-444-5678");
        customer.setCompany("Acme, Inc.");

        Note note = new Note();
        note.setContent("He says he's gonna buy a LOT!!!!");

        Note note2 = new Note();
        note2.setContent("He has changed his mind. THis is only a test.");

//        Set<Note> noteSet = new HashSet<>();
//        noteSet.add(note);
//        noteSet.add(note2);

//        Customer customerAfterSave = customerRepository.save(customer); // same as below.
        customerRepository.save(customer);

        note.setCustomerId(customer.getId());
        note2.setCustomerId(customer.getId());
        noteRepository.save(note);
        noteRepository.save(note2);

        List<Customer> customerList = customerRepository.findAll();
        assertEquals(1, customerList.size());

        Set notesAfterSaveAndRetrieve = customerList.get(0).getNotes();
        assertEquals(2, notesAfterSaveAndRetrieve.size());
    }
}