package com.trilogyed.trainreservation.controller;

import com.trilogyed.trainreservation.model.Route;
import com.trilogyed.trainreservation.service.TrainReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/route")
public class RouteController {

    @Autowired
    TrainReservationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Route createRoute(@RequestBody @Valid Route route) {
        route = service.createRoute(route);
        return route;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Route getRoute(@PathVariable("id") long routeId) {
        Route route = service.getRoute(routeId);
        if (route == null) {
            throw new IllegalArgumentException("Route could not be retrieved for id " + routeId);
        } else {
            return route;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoute(@RequestBody @Valid Route route) {

        if (route==null || route.getId()< 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (route.getId() > 0) {
            service.updateRoute(route);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRoute(@PathVariable("id") long routeId) {
        service.deleteRoute(routeId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Route> getAllRoutes() {
        List<Route> routes = service.getAllRoutes();
        if (routes == null || routes.isEmpty()) {
            throw new IllegalArgumentException("No routes were found");
        } else
            return routes;
    }
}
