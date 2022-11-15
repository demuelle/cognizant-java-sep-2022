package com.money.lotteryticketapi.repository;

import com.money.lotteryticketapi.model.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRepositoryTest {
    @Autowired
    private TicketRepository repo;

    @Test
    public void shouldCreateGetDeleteATicket() {
        Ticket ticket = new Ticket("1-2-3-4",5, true);
        ticket = repo.save(ticket);

        Ticket fromDatabase = repo.findById(ticket.getId()).get();

        assertEquals(ticket, fromDatabase);

        repo.deleteById(ticket.getId());

        Optional<Ticket> afterDelete = repo.findById(ticket.getId());
        assertFalse(afterDelete.isPresent());
    }

}