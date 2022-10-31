package com.trilogyed.trainreservation.controller;

import com.trilogyed.trainreservation.model.Train;
import com.trilogyed.trainreservation.service.TrainReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/train")
public class TrainController {

    @Autowired
    TrainReservationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Train createTrain(@RequestBody @Valid Train train) {
        train = service.createTrain(train);
        return train;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Train getTrain(@PathVariable("id") long trainId) {
        Train train = service.getTrain(trainId);
        if (train == null) {
            throw new IllegalArgumentException("Train could not be retrieved for id " + trainId);
        } else {
            return train;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrain(@RequestBody @Valid Train train) {

        if (train==null || train.getId()< 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (train.getId() > 0) {
            service.updateTrain(train);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrain(@PathVariable("id") long trainId) {
        service.deleteTrain(trainId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Train> getAllTrains() {
        List<Train> trains = service.getAllTrains();
        if (trains == null || trains.isEmpty()) {
            throw new IllegalArgumentException("No trains were found");
        } else
            return trains;
    }
}
