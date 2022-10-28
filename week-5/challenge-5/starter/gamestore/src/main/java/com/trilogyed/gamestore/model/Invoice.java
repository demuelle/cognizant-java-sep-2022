package com.trilogyed.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private long id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String itemType;
    private long itemId;
    private BigDecimal unitPrice;
    private long quantity;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTax() {
        return tax.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getProcessingFee() {
        return processingFee.setScale(2, RoundingMode.HALF_UP);
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal() {
        return total.setScale(2, RoundingMode.HALF_UP);
    }

    public void setTotal(BigDecimal total) {
        this.total = total.setScale(2, RoundingMode.HALF_UP);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getId() == invoice.getId() &&
                getItemId() == invoice.getItemId() &&
                getQuantity() == invoice.getQuantity() &&
                Objects.equals(getName(), invoice.getName()) &&
                Objects.equals(getStreet(), invoice.getStreet()) &&
                Objects.equals(getCity(), invoice.getCity()) &&
                Objects.equals(getState(), invoice.getState()) &&
                Objects.equals(getZipcode(), invoice.getZipcode()) &&
                Objects.equals(getItemType(), invoice.getItemType()) &&
                Objects.equals(getUnitPrice(), invoice.getUnitPrice()) &&
                Objects.equals(getSubtotal(), invoice.getSubtotal()) &&
                Objects.equals(getTax(), invoice.getTax()) &&
                Objects.equals(getProcessingFee(), invoice.getProcessingFee()) &&
                Objects.equals(getTotal(), invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getStreet(), getCity(), getState(), getZipcode(),
                getItemType(), getItemId(), getUnitPrice(), getQuantity(), getSubtotal(), getTax(),
                getProcessingFee(), getTotal());
    }
}
