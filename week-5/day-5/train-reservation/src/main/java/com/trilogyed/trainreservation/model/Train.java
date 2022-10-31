package com.trilogyed.trainreservation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "train_id")
    private Long id;
    @NotEmpty(message = "Name is required")
    private String name;
    @NotEmpty(message = "Model is required")
    private String model;
    @NotEmpty(message = "Manufacturer is required")
    private String manufacturer;
    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Min Year is 1")
    @Max(value = 9999, message = "Max Year is 9999")    private int year;
    @NotNull(message = "Total seats is required")
    @Min(value = 1, message = "Min Total seats is 1")
    @Max(value = 9999, message = "Max Total seats is 9,999")    private int totalSeats;
    @NotEmpty(message = "Seat layout is required")
    private String seatLayout;
    @NotEmpty(message = "Power type is required")
    private String powerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(String seatLayout) {
        this.seatLayout = seatLayout;
    }

    public String getPowerType() {
        return powerType;
    }

    public void setPowerType(String powerType) {
        this.powerType = powerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return year == train.year && totalSeats == train.totalSeats && Objects.equals(id, train.id) && Objects.equals(name, train.name) && Objects.equals(model, train.model) && Objects.equals(manufacturer, train.manufacturer) && Objects.equals(seatLayout, train.seatLayout) && Objects.equals(powerType, train.powerType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, model, manufacturer, year, totalSeats, seatLayout, powerType);
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", year=" + year +
                ", totalSeats=" + totalSeats +
                ", seatLayout='" + seatLayout + '\'' +
                ", powerType='" + powerType + '\'' +
                '}';
    }
}
