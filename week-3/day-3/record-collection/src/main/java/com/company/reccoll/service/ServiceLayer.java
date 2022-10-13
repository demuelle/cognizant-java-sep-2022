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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ServiceLayer {
    private AlbumRepository albumRepository;
    private ArtistRepository artistRepository;
    private LabelRepository labelRepository;
    private TrackRepository trackRepository;

    @Autowired
    public ServiceLayer(AlbumRepository albumRepository, ArtistRepository artistRepository, LabelRepository labelRepository, TrackRepository trackRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.labelRepository = labelRepository;
        this.trackRepository = trackRepository;
    }

    // this will only work if the AlbumViewModel has an artist and a label
    // that are already in the database
    @Transactional
    public AlbumViewModel saveAlbum(AlbumViewModel viewModel) {
        // write album information to the database
        Album a = new Album();
        a.setTitle(viewModel.getTitle());
        Artist theNewArtist = viewModel.getArtist();
        int theNewArtistId = theNewArtist.getId();;
        a.setArtistId(theNewArtistId);
        a.setLabelId(viewModel.getLabel().getId());
        a.setReleaseDate(viewModel.getReleaseDate());
        a.setListPrice(viewModel.getListPrice());

        a = albumRepository.save(a);

        // get the album id and set the album id field for each track
        Set<Track> tracks = viewModel.getTracks();

        for(Track track : tracks) {
            track.setAlbumId(a.getId());
            // write each track to the database
            trackRepository.save(track);
        }

        // get the list of tracks back out of the database because now they have full album id info
        tracks = new HashSet<>(trackRepository.findAllTracksByAlbumId(a.getId()));

        // reassemble my view model to return
        viewModel.setTracks(tracks);
        viewModel.setId(a.getId());

        return viewModel;
    }

    public AlbumViewModel findAlbum(int id) {
        AlbumViewModel returnVal = null;

        Optional<Album> album = albumRepository.findById(id);

        if (album.isPresent()) {
            // create a view model using the information from the album
            returnVal = buildAlbumViewModel(album.get());
        }

        return returnVal;
    }

    private AlbumViewModel buildAlbumViewModel(Album album) {
        AlbumViewModel returnVal = new AlbumViewModel();
        returnVal.setId(album.getId());
        returnVal.setTitle(album.getTitle());

        int artistId = album.getArtistId();
        Optional<Artist> artist = artistRepository.findById(artistId);
        if (artist.isPresent()) {
            returnVal.setArtist(artist.get());
        }

        int labelId = album.getLabelId();
        Optional<Label> label = labelRepository.findById(labelId);
        if (label.isPresent()) {
            returnVal.setLabel(label.get());
        }

        returnVal.setReleaseDate(album.getReleaseDate());
        returnVal.setListPrice(album.getListPrice());

        List<Track> trackList = trackRepository.findAllTracksByAlbumId(album.getId());
        // albumviewmodel contains a set of tracks, so we need to create a set
        Set<Track> trackSet = new HashSet<>(trackList);
        returnVal.setTracks(trackSet);

        return returnVal;
    }

    public List<AlbumViewModel> getAllAlbums() {
        List<AlbumViewModel> returnVal = new ArrayList<>();
        // get all the albums from the database
        List<Album> allAlbums = albumRepository.findAll();

        // iterate! For each one, call the buildAlbumViewModel method
        for (Album album : allAlbums) {
            AlbumViewModel avm = buildAlbumViewModel(album);
            // assemble them in a list and return
            returnVal.add(avm);
        }

        return returnVal;
    }
}
