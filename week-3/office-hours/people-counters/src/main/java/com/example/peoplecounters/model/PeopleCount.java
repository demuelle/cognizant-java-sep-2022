package com.example.peoplecounters.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PeopleCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int sensorId;
    private int startHour;
    private int inCount;
    private int outCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getInCount() {
        return inCount;
    }

    public void setInCount(int inCount) {
        this.inCount = inCount;
    }

    public int getOutCount() {
        return outCount;
    }

    public void setOutCount(int outCount) {
        this.outCount = outCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeopleCount that = (PeopleCount) o;
        return sensorId == that.sensorId && startHour == that.startHour && inCount == that.inCount && outCount == that.outCount && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sensorId, startHour, inCount, outCount);
    }

    @Override
    public String toString() {
        return "PeopleCount{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", startHour=" + startHour +
                ", inCount=" + inCount +
                ", outCount=" + outCount +
                '}';
    }
}
