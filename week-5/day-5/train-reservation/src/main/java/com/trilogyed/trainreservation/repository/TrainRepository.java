package com.trilogyed.trainreservation.repository;

import com.trilogyed.trainreservation.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train, Long> {
}
