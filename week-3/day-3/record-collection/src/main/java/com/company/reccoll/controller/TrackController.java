package com.company.reccoll.controller;

import com.company.reccoll.model.Track;
import com.company.reccoll.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TrackController {
    @Autowired
    TrackRepository repo;

    @GetMapping("/tracks")
    public List<Track> getTracks() {
        return repo.findAll();
    }

    @GetMapping("/tracks/{id}")
    public Track getTrackById(@PathVariable int id) {
        Optional<Track> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping("/tracks")
    @ResponseStatus(HttpStatus.CREATED)
    public Track addTrack(@RequestBody Track track) {
        return repo.save(track);
    }

    @PutMapping("/tracks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrack(@RequestBody Track track) {
        repo.save(track);
    }

    @DeleteMapping("/tracks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable int id) {
        repo.deleteById(id);
    }
}
