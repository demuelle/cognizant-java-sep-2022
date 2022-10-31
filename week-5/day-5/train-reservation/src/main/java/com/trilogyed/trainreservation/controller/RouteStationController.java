package com.trilogyed.trainreservation.controller;

import com.trilogyed.trainreservation.model.RouteStation;
import com.trilogyed.trainreservation.service.TrainReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/routestation")
public class RouteStationController {

    @Autowired
    TrainReservationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RouteStation createRouteStation(@RequestBody @Valid RouteStation routeStation) {
        routeStation = service.createRouteStation(routeStation);
        return routeStation;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RouteStation getRouteStation(@PathVariable("id") long routeStationId) {
        RouteStation routeStation = service.getRouteStation(routeStationId);
        if (routeStation == null) {
            throw new IllegalArgumentException("Route Station could not be retrieved for id " + routeStationId);
        } else {
            return routeStation;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRouteStation(@RequestBody @Valid RouteStation routeStation) {

        if (routeStation==null || routeStation.getId()< 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (routeStation.getId() > 0) {
            service.updateRouteStation(routeStation);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRouteStation(@PathVariable("id") long routeStationId) { service.deleteRoute(routeStationId); }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<RouteStation> getAllRouteStations() {
        List<RouteStation> routeStations = service.getAllRouteStations();
        if (routeStations == null || routeStations.isEmpty()) {
            throw new IllegalArgumentException("No route stations were found");
        } else
            return routeStations;
    }
}
