package com.trilogyed.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "fee")
public class ProcessingFee {

    @Id
    @Column(name = "product_type")
    private String productType;
    private BigDecimal fee;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        return fee.setScale(2, RoundingMode.HALF_UP);
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return Objects.equals(getProductType(), that.getProductType()) &&
                Objects.equals(getFee(), that.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getFee());
    }
}
