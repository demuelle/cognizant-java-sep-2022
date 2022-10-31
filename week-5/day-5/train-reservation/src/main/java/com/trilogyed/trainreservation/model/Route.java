package com.trilogyed.trainreservation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;
    @NotEmpty(message = "Route name is required")
    private String routeName;
    @NotNull(message = "Train id is required")
    @Min(value = 1, message = "Min train id is 1")
    private int trainId;
    @NotNull(message = "Source station id is required")
    @Min(value = 1, message = "Min source station id is 1")
    private int sourceStationId;
    @NotNull(message = "Destination station id is required")
    @Min(value = 1, message = "Min destination station id is 1")
    private int destinationStationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) { this.routeName = routeName; }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return trainId == route.trainId && sourceStationId == route.sourceStationId && destinationStationId == route.destinationStationId && Objects.equals(id, route.id) && Objects.equals(routeName, route.routeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, routeName, trainId, sourceStationId, destinationStationId);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", routeName='" + routeName + '\'' +
                ", trainId=" + trainId +
                ", sourceStationId=" + sourceStationId +
                ", destinationStationId=" + destinationStationId +
                '}';
    }
}
