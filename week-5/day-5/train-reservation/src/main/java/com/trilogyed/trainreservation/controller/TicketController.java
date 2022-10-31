package com.trilogyed.trainreservation.controller;

import com.trilogyed.trainreservation.model.Ticket;
import com.trilogyed.trainreservation.service.TrainReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

    @Autowired
    TrainReservationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket createTicket(@RequestBody @Valid Ticket ticket) {
        ticket = service.createTicket(ticket);
        return ticket;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ticket getTicket(@PathVariable("id") long ticketId) {
        Ticket ticket = service.getTicket(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket could not be retrieved for id " + ticketId);
        } else {
            return ticket;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTicket(@RequestBody @Valid Ticket ticket) {

        if (ticket==null || ticket.getId()< 1) {
            throw new IllegalArgumentException("Id in path must match id in view model");
        } else if (ticket.getId() > 0) {
            service.updateTicket(ticket);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable("id") long ticketId) {
        service.deleteTicket(ticketId);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = service.getAllTickets();
        if (tickets == null || tickets.isEmpty()) {
            throw new IllegalArgumentException("No tickets were found");
        } else
            return tickets;
    }
}
