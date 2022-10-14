package com.company.reccoll.service;

import com.company.reccoll.model.Album;
import com.company.reccoll.model.Artist;
import com.company.reccoll.model.Label;
import com.company.reccoll.model.Track;
import com.company.reccoll.repository.AlbumRepository;
import com.company.reccoll.repository.ArtistRepository;
import com.company.reccoll.repository.LabelRepository;
import com.company.reccoll.repository.TrackRepository;
import com.company.reccoll.viewmodel.AlbumViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private LabelRepository labelRepository;
    private TrackRepository trackRepository;

    private ServiceLayer serviceLayer;

    @Before
    public void setUp() {
        setUpArtistRepositoryMock();
        setUpLabelRepositoryMock();
        setUpAlbumRepositoryMock();
        setUpTrackRepositoryMock();

        serviceLayer = new ServiceLayer(albumRepository, artistRepository, labelRepository, trackRepository);
    }

    private void setUpAlbumRepositoryMock() {
        albumRepository = mock(AlbumRepository.class);
    }

    private void setUpArtistRepositoryMock() {
        artistRepository = mock(ArtistRepository.class);
        Artist artist = new Artist();
        artist.setId(45);
        artist.setInstagram("@RockStar");
        artist.setName("The GOAT");
        artist.setTwitter("@TheRockStar");

        Artist artist2 = new Artist();
        artist2.setInstagram("@RockStar");
        artist2.setName("The GOAT");
        artist2.setTwitter("@TheRockStar");

        List aList = new ArrayList();
        aList.add(artist);

        doReturn(artist).when(artistRepository).save(artist2);
        doReturn(Optional.of(artist)).when(artistRepository).findById(45);
        doReturn(aList).when(artistRepository).findAll();
    }

    private void setUpLabelRepositoryMock() {
        labelRepository = mock(LabelRepository.class);
        Label label = new Label();
        label.setId(10);
        label.setName("Blue Note");
        label.setWebsite("www.bluenote.com");

        Label label2 = new Label();
        label2.setName("Blue Note");
        label2.setWebsite("www.bluenote.com");

        List lList = new ArrayList<>();
        lList.add(label);

        doReturn(label).when(labelRepository).save(label2);
        doReturn(Optional.of(label)).when(labelRepository).findById(10);
        doReturn(lList).when(labelRepository).findAll();
    }

    private void setUpTrackRepositoryMock() {
        trackRepository = mock(TrackRepository.class);
        Track track = new Track();
        track.setId(1);
        track.setAlbumId(1);
        track.setRuntime(180);
        track.setTitle("Number 1 Hit!");

        Track track2 = new Track();
        track.setAlbumId(1);
        track.setRuntime(180);
        track.setTitle("Number 1 Hit!");

        List tList = new ArrayList<>();
        tList.add(track);

        doReturn(track).when(trackRepository).save(track2);
        doReturn(Optional.of(track)).when(trackRepository).findById(1);
        doReturn(tList).when(trackRepository).findAll();
        doReturn(tList).when(trackRepository).findByAlbumId(1);
    }

    @Test
    public void shouldSaveAlbum() {
        // Arrange
    }
}