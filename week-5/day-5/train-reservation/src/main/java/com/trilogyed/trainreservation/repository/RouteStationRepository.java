package com.trilogyed.trainreservation.repository;

import com.trilogyed.trainreservation.model.RouteStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteStationRepository extends JpaRepository<RouteStation, Long> {
}
