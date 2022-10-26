package com.twou.rsvp.repository;

import com.twou.rsvp.model.Rsvp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RsvpRepositoryTest {

    @Autowired
    private RsvpRepository repo;

    @Test
    public void addGetDeleteRsvp() {
        Rsvp rsvp = new Rsvp();
        rsvp.setGuestName("John Doe");
        rsvp.setTotalAttending(2);
        rsvp = repo.save(rsvp);
        Optional<Rsvp> fromDao = repo.findById(rsvp.getId());
        assertEquals(fromDao.get(), rsvp);
        repo.deleteById(rsvp.getId());
        fromDao = repo.findById(rsvp.getId());
        assertFalse(fromDao.isPresent());
    }

    @Test
    public void getAllRsvps() {
        Rsvp rsvp = new Rsvp();
        rsvp.setGuestName("Sally Smith");
        rsvp.setTotalAttending(4);
        repo.save(rsvp);

        rsvp = new Rsvp();
        rsvp.setGuestName("George Smith");
        rsvp.setTotalAttending(3);
        repo.save(rsvp);

        List<Rsvp> rsvps = repo.findAll();

        assertEquals(2, rsvps.size());
    }

    @Test
    public void updateRsvp() {
        Rsvp rsvp = new Rsvp();
        rsvp.setGuestName("Joe Jones");
        rsvp.setTotalAttending(5);
        rsvp = repo.save(rsvp);

        rsvp.setGuestName("NEW NAME");
        repo.save(rsvp);

        Optional<Rsvp> fromDao = repo.findById(rsvp.getId());
        assertEquals(rsvp, fromDao.get());
    }
}