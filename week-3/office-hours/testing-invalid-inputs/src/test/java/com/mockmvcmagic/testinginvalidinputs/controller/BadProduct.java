package com.mockmvcmagic.testinginvalidinputs.controller;

import java.util.Objects;

public class BadProduct {
    private String brand;
    private String description;
    private String quantity;
    private Double price;

    public BadProduct(String brand, String description, String quantity, Double price) {
        this.brand = brand;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BadProduct that = (BadProduct) o;
        return Objects.equals(brand, that.brand) && Objects.equals(description, that.description) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, description, quantity, price);
    }

    @Override
    public String toString() {
        return "BadProduct{" +
                "brand='" + brand + '\'' +
                ", description='" + description + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price=" + price +
                '}';
    }
}
