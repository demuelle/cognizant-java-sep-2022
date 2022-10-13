package com.company.reccoll.repository;

import com.company.reccoll.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtistRepositoryTests {

    @Autowired
    ArtistRepository artistRepository;

    @Before
    public void setUp() throws Exception {
        artistRepository.deleteAll();
    }

    @Test
    public void addGetDeleteArtist() {

        Artist artist = new Artist();
        artist.setName("Rock Star");
        artist.setInstagram("@RockStart");
        artist.setTwitter(("@TheRockStar"));

        artist = artistRepository.save(artist);

        Optional<Artist> artist1 = artistRepository.findById(artist.getId());

        assertEquals(artist1.get(), artist);

        artistRepository.deleteById(artist.getId());

        artist1 = artistRepository.findById(artist.getId());

        assertFalse(artist1.isPresent());
    }

    @Test
    public void updateArtist() {

        Artist artist = new Artist();
        artist.setName("Rock Star");
        artist.setInstagram("@RockStart");
        artist.setTwitter(("@TheRockStar"));

        artist = artistRepository.save(artist);

        artist.setName("Pop Star");
        artist.setInstagram("@PopStar");
        artist.setTwitter("@ThePopstar");

        artistRepository.save(artist);

        Optional<Artist> artist1 = artistRepository.findById(artist.getId());
        assertEquals(artist1.get(), artist);
    }

    @Test
    public void getAllArtists() {

        Artist artist = new Artist();
        artist.setName("Rock Star");
        artist.setInstagram("@RockStart");
        artist.setTwitter(("@TheRockStar"));

        artist = artistRepository.save(artist);

        artist = new Artist();
        artist.setName("Super Star");
        artist.setInstagram("@SuperStart");
        artist.setTwitter(("@TheSuperStar"));

        artist = artistRepository.save(artist);

        List<Artist> aList = artistRepository.findAll();
        assertEquals(aList.size(), 2);

    }

}