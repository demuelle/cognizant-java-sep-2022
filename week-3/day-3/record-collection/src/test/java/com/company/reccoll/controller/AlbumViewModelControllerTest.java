package com.company.reccoll.controller;

import com.company.reccoll.model.Artist;
import com.company.reccoll.model.Label;
import com.company.reccoll.model.Track;
import com.company.reccoll.service.ServiceLayer;
import com.company.reccoll.viewmodel.AlbumViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumViewModelController.class)
public class AlbumViewModelControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    private ServiceLayer serviceLayer;

    private String inputAlbumJson;
    private String outputAlbumJson;

    @Before
    public void setUp() throws Exception {
        AlbumViewModel inputAlbumViewModel = new AlbumViewModel();
        inputAlbumViewModel.setTitle("Katy and Tayty");
        inputAlbumViewModel.setReleaseDate(LocalDate.of(2022, 10, 14));
        inputAlbumViewModel.setListPrice(new BigDecimal("22.88"));

        Artist artist = new Artist();
        artist.setName("Taylor Swift");
        artist.setInstagram("@taylorSwift");
        artist.setTwitter("@taylorS");
        artist.setId(100);
        inputAlbumViewModel.setArtist(artist);

        Label label = new Label();
        label.setName("good music records");
        label.setWebsite("www.gmr.com");
        label.setId(55);
        inputAlbumViewModel.setLabel(label);

        Track track1 = new Track();
        track1.setTitle("I'm Mad At You");
        track1.setRuntime(210);
        Track track2 = new Track();
        track2.setTitle("I'm Madder Now");
        track2.setRuntime(199);
        List<Track> tracks = Arrays.asList(track1, track2);
        inputAlbumViewModel.setTracks(tracks);

        inputAlbumJson = mapper.writeValueAsString(inputAlbumViewModel);
        AlbumViewModel outputAlbumViewModel = new AlbumViewModel();
        outputAlbumViewModel.setTitle("Katy and Tayty");
        outputAlbumViewModel.setReleaseDate(LocalDate.of(2022, 10, 14));
        outputAlbumViewModel.setListPrice(new BigDecimal("22.88"));

        outputAlbumViewModel.setArtist(artist);
        outputAlbumViewModel.setLabel(label);

        Track outtrack1 = new Track();
        outtrack1.setTitle("I'm Mad At You");
        outtrack1.setRuntime(210);
        outtrack1.setId(1);
        outtrack1.setAlbumId(89);
        Track outtrack2 = new Track();
        outtrack2.setTitle("I'm Madder Now");
        outtrack2.setRuntime(199);
        outtrack2.setId(2);
        outtrack2.setAlbumId(89);
        List<Track> outtracks = Arrays.asList(outtrack1, outtrack1);
        outputAlbumViewModel.setTracks(outtracks);
        outputAlbumViewModel.setId(89);
        outputAlbumJson = mapper.writeValueAsString(outputAlbumViewModel);

        doReturn(outputAlbumViewModel).when(serviceLayer).saveAlbum(inputAlbumViewModel);
        doReturn(outputAlbumViewModel).when(serviceLayer).findAlbum(89);
    }

    @Test
    public void shouldCreateNewAlbum() throws Exception {
        mockMvc.perform(post("/completeAlbum")
                .content(inputAlbumJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumJson));
    }

}