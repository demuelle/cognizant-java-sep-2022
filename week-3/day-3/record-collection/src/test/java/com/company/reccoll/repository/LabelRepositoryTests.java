package com.company.reccoll.repository;

import com.company.reccoll.model.Label;
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
public class LabelRepositoryTests {

    @Autowired
    TrackRepository trackRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    LabelRepository labelRepository;

    @Before
    public void setUp() throws Exception {
        trackRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        labelRepository.deleteAll();
    }

    @Test
    public void addGetDeleteLabel() {

        Label label = new Label();
        label.setName("Intesrcope");
        label.setWebsite("www.intersope.com");
        labelRepository.save(label);

        Optional<Label> label1 = labelRepository.findById(label.getId());

        assertEquals(label1.get(), label);

        labelRepository.deleteById(label.getId());

        label1 = labelRepository.findById(label.getId());

        assertFalse(label1.isPresent());

    }

    @Test
    public void getAllLabels() {

        Label label = new Label();
        label.setName("Intesrcope");
        label.setWebsite("www.intersope.com");
        labelRepository.save(label);

        label = new Label();
        label.setName("Island");
        label.setWebsite("www.island.com");
        labelRepository.save(label);

        List<Label> lList = labelRepository.findAll();

        assertEquals(lList.size(), 2);

    }

    @Test
    public void updateLabel() {

        Label label = new Label();
        label.setName("Intesrcope");
        label.setWebsite("www.intersope.com");
        labelRepository.save(label);

        label.setName("NEW NAME");
        label.setWebsite("NEW WEBSITE");
        labelRepository.save(label);

        Optional<Label> label1 = labelRepository.findById(label.getId());

        assertEquals(label1.get(), label);
    }
}