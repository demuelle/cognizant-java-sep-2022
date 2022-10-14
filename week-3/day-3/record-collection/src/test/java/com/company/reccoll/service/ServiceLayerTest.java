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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

    @Test
    public void doSomething() {
        System.out.println("This is here so my before runs.");
    }
    private void setUpAlbumRepositoryMock() {
        albumRepository = mock(AlbumRepository.class);
        Album album = new Album();
        album.setId(1);
        album.setArtistId(45);
        album.setLabelId(10);
        album.setReleaseDate(LocalDate.of(1984, 6, 22));
        album.setListPrice(new BigDecimal("18.99"));
        album.setTitle("All the Hits!");

        Album album2 = new Album();
        album2.setArtistId(45);
        album2.setLabelId(10);
        album2.setReleaseDate(LocalDate.of(1984, 6, 22));
        album2.setListPrice(new BigDecimal("18.99"));
        album2.setTitle("All the Hits!");
        
        List aList = new ArrayList<>();
        aList.add(album);

        doReturn(album).when(albumRepository).save(album2);
        doReturn(Optional.of(album)).when(albumRepository).findById(1);
        doReturn(aList).when(albumRepository).findAll();
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

        List<Artist> aList = new ArrayList<>();
        aList.add(artist);

        doReturn(artist).when(artistRepository).save(artist2);
        doReturn(Optional.of(artist)).when(artistRepository).findById(45);
        doReturn(aList).when(artistRepository).findAll();

//        Optional<Artist> trickyAnswer = artistRepository.findById(100);
//
//        System.out.println("trickyAnswer is " + trickyAnswer);
//        Artist x = artistRepository.save(artist2);
//        Optional<Artist> y = artistRepository.findById(45);
//        List<Artist> z = artistRepository.findAll();
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
        AlbumViewModel inputAlbumViewModel = new AlbumViewModel();
        inputAlbumViewModel.setTitle("All the Hits!");
        inputAlbumViewModel.setReleaseDate(LocalDate.of(1984, 6, 22));
        inputAlbumViewModel.setListPrice(new BigDecimal("18.99"));
        
        Artist inputAlbumViewModelArtist = new Artist();
        inputAlbumViewModelArtist.setId(45);
        inputAlbumViewModelArtist.setInstagram("@RockStar");
        inputAlbumViewModelArtist.setName("The GOAT");
        inputAlbumViewModelArtist.setTwitter("@TheRockStar");
        inputAlbumViewModel.setArtist(inputAlbumViewModelArtist);
        
        Label inputAlbumViewModelLabel = new Label();
        inputAlbumViewModelLabel.setId(10);
        inputAlbumViewModelLabel.setName("Blue Note");
        inputAlbumViewModelLabel.setWebsite("www.bluenote.com");
        inputAlbumViewModel.setLabel(inputAlbumViewModelLabel);

        Track inputTrack = new Track();
        inputTrack.setRuntime(180);
        inputTrack.setTitle("Number 1 Hit!");
        List<Track> inputTrackList = Arrays.asList(inputTrack);
        inputAlbumViewModel.setTracks(inputTrackList);

        AlbumViewModel expectedOutput = new AlbumViewModel();
        expectedOutput.setId(1);
        expectedOutput.setTitle("All the Hits!");
        expectedOutput.setReleaseDate(LocalDate.of(1984, 6, 22));
        expectedOutput.setListPrice(new BigDecimal("18.99"));

        Artist outputArtist = new Artist();
        outputArtist.setId(45);
        outputArtist.setInstagram("@RockStar");
        outputArtist.setName("The GOAT");
        outputArtist.setTwitter("@TheRockStar");
        expectedOutput.setArtist(outputArtist);

        Label outputLabel = new Label();
        outputLabel.setId(10);
        outputLabel.setName("Blue Note");
        outputLabel.setWebsite("www.bluenote.com");
        expectedOutput.setLabel(outputLabel);

        Track outputTrack = new Track();
        outputTrack.setId(1);
        outputTrack.setAlbumId(1);
        outputTrack.setRuntime(180);
        outputTrack.setTitle("Number 1 Hit!");
        List<Track> outputTrackList = Arrays.asList(outputTrack);
        expectedOutput.setTracks(outputTrackList);
        
        // Act
        AlbumViewModel actualAlbumViewModel = serviceLayer.saveAlbum(inputAlbumViewModel);
        
        // Assert
        assertEquals(expectedOutput, actualAlbumViewModel);

    }
}