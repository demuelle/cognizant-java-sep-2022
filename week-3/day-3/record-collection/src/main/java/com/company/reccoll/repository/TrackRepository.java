package com.company.reccoll.repository;

import com.company.reccoll.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
    List<Track> findAllTracksByAlbumId(int albumId);
}
