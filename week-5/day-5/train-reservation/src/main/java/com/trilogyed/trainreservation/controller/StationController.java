package com.trilogyed.trainreservation.controller;

import com.trilogyed.trainreservation.model.Station;
import com.trilogyed.trainreservation.service.TrainReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/station")
public class StationController {

    @Autowired
    TrainReservationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Station createStation(@RequestBody @Valid Station station) {
        station = service.createStation(station);
        return station;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Station getStation(@PathVariable("id") long stationId) {
        Station station = service.getStation(stationId);
        if (station == null) {
            throw new IllegalArgumentException("Station could not be retrieved for id " + stationId);
        } else {
            return station;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStation(@RequestBody @Valid Station station) {

        if (station==null || station.getId()< 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (station.getId() > 0) {
            service.updateStation(station);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStation(@PathVariable("id") long stationId) {
        service.deleteStation(stationId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Station> getAllStations() {
        List<Station> stations = service.getAllStations();
        if (stations == null || stations.isEmpty()) {
            throw new IllegalArgumentException("No stations were found");
        } else
            return stations;
    }
}
