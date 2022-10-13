package com.company.reccoll.controller;

import com.company.reccoll.model.Artist;
import com.company.reccoll.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    @Autowired
    ArtistRepository repo;

    @GetMapping()
    public List<Artist> getArtists() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable int id) {
        Optional<Artist> returnVal = repo.findById(id);
        if (returnVal.isPresent()) {
            return returnVal.get();
        } else {
            return null;
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Artist addArtist(@RequestBody Artist artist) {
        return repo.save(artist);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody Artist artist) {
        repo.save(artist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable int id) {
        repo.deleteById(id);
    }
}
