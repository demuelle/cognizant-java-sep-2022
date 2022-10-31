package com.trilogyed.trainreservation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Long id;
    @NotNull(message = "Route id is required")
    @Min(value = 1, message = "Min route id is 1")
    private int routeId;
    @NotNull(message = "Customer id is required")
    @Min(value = 1, message = "Min customer id is 1")
    private int customerId;
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotNull(message = "Source station id is required")
    @Min(value = 1, message = "Min source station id is 1")
    private int sourceStationId;
    @NotNull(message = "Destination station id is required")
    @Min(value = 1, message = "Min destination station id is 1")
    private int destinationStationId;
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @NotNull(message = "Ticket date is required")
    private Date ticketDate;
    @NotEmpty(message = "Seat no is required")
    private String seatNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) { this.routeId = routeId; }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getSourceStationId() {
        return sourceStationId;
    }

    public void setSourceStationId(int sourceStationId) {
        this.sourceStationId = sourceStationId;
    }

    public int getDestinationStationId() {
        return destinationStationId;
    }

    public void setDestinationStationId(int destinationStationId) {
        this.destinationStationId = destinationStationId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return routeId == ticket.routeId && customerId == ticket.customerId && sourceStationId == ticket.sourceStationId && destinationStationId == ticket.destinationStationId && Objects.equals(id, ticket.id) && Objects.equals(firstName, ticket.firstName) && Objects.equals(price, ticket.price) && Objects.equals(ticketDate, ticket.ticketDate) && Objects.equals(seatNo, ticket.seatNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeId, customerId, firstName, sourceStationId, destinationStationId, price, ticketDate, seatNo);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", routeId=" + routeId +
                ", customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", sourceStationId=" + sourceStationId +
                ", destinationStationId=" + destinationStationId +
                ", price=" + price +
                ", ticketDate=" + ticketDate +
                ", seatNo='" + seatNo + '\'' +
                '}';
    }
}
