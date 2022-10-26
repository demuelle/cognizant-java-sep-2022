package com.twou.rsvp.controller;

import com.twou.rsvp.model.Rsvp;
import com.twou.rsvp.repository.RsvpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rsvp")
@CacheConfig(cacheNames = {"rsvps"})

public class RsvpController {
    @Autowired
    private RsvpRepository repo;

    @PostMapping
    @CachePut(key = "#result.getId()")
    @ResponseStatus(HttpStatus.CREATED)
    public Rsvp createRsvp(@RequestBody Rsvp rsvp) {
        System.out.println("Creating RSVP: " + rsvp);
        return repo.save(rsvp);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Rsvp> getAllRsvps() {
        System.out.println("Getting all RSVPs");
        return repo.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(key = "#rsvp.getId()")
    public void updateRsvp(@PathVariable Long id, @RequestBody Rsvp rsvp) {
        if (id != rsvp.getId()) {
            throw new RuntimeException("You did it wrong.");
        }
        System.out.println("Updating RSVP with id " + id);
        repo.save(rsvp);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict
    public void deleteRsvp(@PathVariable Long id) {
        System.out.println("Deleting RSVP with id " + id);
        repo.deleteById(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable
    public Rsvp getRsvpById(@PathVariable Long id) {
        System.out.println("Getting RSVP with id " + id);
        return repo.findById(id).get();
    }
}
