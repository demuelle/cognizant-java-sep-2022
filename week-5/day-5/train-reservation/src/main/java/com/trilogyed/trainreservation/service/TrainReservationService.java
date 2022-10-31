package com.trilogyed.trainreservation.service;

import com.trilogyed.trainreservation.model.Customer;
import com.trilogyed.trainreservation.model.Train;
import com.trilogyed.trainreservation.repository.*;
import com.trilogyed.trainreservation.model.Station;
import com.trilogyed.trainreservation.model.Route;
import com.trilogyed.trainreservation.model.RouteStation;
import com.trilogyed.trainreservation.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TrainReservationService {

    CustomerRepository customerRepo;
    TrainRepository trainRepo;
    StationRepository stationRepo;
    RouteRepository routeRepo;
    RouteStationRepository routeStationRepo;
    TicketRepository ticketRepo;

    @Autowired
    public TrainReservationService(CustomerRepository customerRepo, TrainRepository trainRepo, StationRepository stationRepo,
                                   RouteRepository routeRepo, RouteStationRepository routeStationRepo, TicketRepository ticketRepo)
    {
        this.customerRepo = customerRepo;
        this.trainRepo = trainRepo;
        this.stationRepo = stationRepo;
        this.routeRepo = routeRepo;
        this.routeStationRepo = routeStationRepo;
        this.ticketRepo = ticketRepo;
    }

    //CUSTOMER SERVICE LAYER

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer getCustomer(long id) {
        Optional<Customer> tCustomer = customerRepo.findById(id);
        return tCustomer.orElse(null);
    }

    public void updateCustomer(Customer customer) {
        //make sure the Customer exists. and if not, throw exception...
        if (this.getCustomer(customer.getId())==null)
            throw new IllegalArgumentException("No such Customer to update (id: " + customer.getId() + ").");

        customerRepo.save(customer);
    }

    public void deleteCustomer(long id) {
        customerRepo.deleteById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    //TRAIN SERVICE LAYER

    public Train createTrain(Train train) {
        return trainRepo.save(train);
    }

    public Train getTrain(long id) {
        return trainRepo.findById(id).orElse(null);
    }

    public void updateTrain(Train train) {

        //make sure the Train exists. and if not, throw exception...
        if (this.getTrain(train.getId())==null)
            throw new IllegalArgumentException("No such Train to update.");

        trainRepo.save(train);
    }

    public void deleteTrain(long id) {
        trainRepo.deleteById(id);
    }

    public List<Train> getAllTrains() {
        return trainRepo.findAll();
    }

    //ROUTE SERVICE LAYER

    public Route createRoute(Route route) {

        return routeRepo.save(route);
    }

    public Route getRoute(long id) {
        return routeRepo.findById(id).orElse(null);
    }

    public void updateRoute(Route route) {
        //make sure the Route exists. and if not, throw exception...
        if (this.getRoute(route.getId())==null)
            throw new IllegalArgumentException("No such Route to update.");
        routeRepo.save(route);
    }

    public void deleteRoute(long id) {
        routeRepo.deleteById(id);
    }

    public List<Route> getAllRoutes() {
        return routeRepo.findAll();
    }

    //STATION SERVICE LAYER

    public Station createStation(Station station) {
        return stationRepo.save(station);
    }

    public Station getStation(long id) {
        return stationRepo.findById(id).orElse(null);
    }

    public void updateStation(Station station) {
        if (this.getStation(station.getId())==null)
            throw new IllegalArgumentException("No such Station to update.");
        stationRepo.save(station);
    }

    public void deleteStation(long id) {
        stationRepo.deleteById(id);
    }

    public List<Station> getAllStations() {
        return stationRepo.findAll();
    }

    //ROUTE STATION SERVICE LAYER

    public RouteStation createRouteStation(RouteStation routeStation) {

        return routeStationRepo.save(routeStation);
    }

    public RouteStation getRouteStation(long id) {
        return routeStationRepo.findById(id).orElse(null);
    }

    public void updateRouteStation(RouteStation routeStation) {
        //make sure the Route Station exists. and if not, throw exception...
        if (this.getRouteStation(routeStation.getId())==null)
            throw new IllegalArgumentException("No such Route Station to update.");

        routeStationRepo.save(routeStation);
    }

    public void deleteRouteStation(long id) {
        routeStationRepo.deleteById(id);
    }

    public List<RouteStation> getAllRouteStations() {
        return routeStationRepo.findAll();
    }

    //TICKET SERVICE LAYER

    public Ticket createTicket(Ticket ticket) {

        return ticketRepo.save(ticket);
    }

    public Ticket getTicket(long id) {
        return ticketRepo.findById(id).orElse(null);
    }

    public void updateTicket(Ticket ticket) {
        //make sure the Ticket exists. and if not, throw exception...
        if (this.getTicket(ticket.getId())==null)
            throw new IllegalArgumentException("No such Ticket to update.");

        ticketRepo.save(ticket);
    }

    public void deleteTicket(long id) {
        ticketRepo.deleteById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }
}
