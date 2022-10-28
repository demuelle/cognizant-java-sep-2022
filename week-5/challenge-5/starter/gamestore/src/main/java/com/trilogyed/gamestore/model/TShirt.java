package com.trilogyed.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tshirt")
public class TShirt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tshirt_id")
    private long id;
    private String size;
    private String color;
    private String description;
    private BigDecimal price;
    private long quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TShirt)) return false;
        TShirt tShirt = (TShirt) o;
        return getId() == tShirt.getId() &&
                getQuantity() == tShirt.getQuantity() &&
                Objects.equals(getSize(), tShirt.getSize()) &&
                Objects.equals(getColor(), tShirt.getColor()) &&
                Objects.equals(getDescription(), tShirt.getDescription()) &&
                Objects.equals(getPrice(), tShirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }
}
