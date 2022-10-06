package com.company.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Min;

public class City {

    @NotEmpty(message = "You must supply a name for the city.")
    private String name;
    @NotEmpty(message = "You must supply a state.")
    private String state;
    @Min(value = 1, message = "You must supply the population.")
    private int population;
    private boolean capital;

    public City() {}

    public City(String name, String state, int peopleCount, boolean capital) {
        this.name = name;
        this.state = state;
        this.population = peopleCount;
        this.capital = capital;
    }

    public City(String name, String state, int peopleCount) {
        this(name, state, peopleCount, false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public boolean isCapital() {
        return capital;
    }

    public void setCapital(boolean capital) {
        this.capital = capital;
    }

}
