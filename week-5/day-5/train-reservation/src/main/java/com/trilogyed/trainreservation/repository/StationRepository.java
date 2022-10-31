package com.trilogyed.trainreservation.repository;

import com.trilogyed.trainreservation.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
}
