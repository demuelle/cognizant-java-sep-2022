package org.coffee.coffeeordering.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@Table(name="coffee")
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="bean_type")
    private String beanType;  // arrabica or robosto
    private String fermentation; // the manure - thanks, Steph
    private String roast;
    private String origin;
    private BigInteger acidity;

    public Coffee() {
    }

    public Coffee(Long id, String beanType, String fermentation, String roast, String origin, BigInteger acidity, BigDecimal pricePerPound) {
        this.id = id;
        this.beanType = beanType;
        this.fermentation = fermentation;
        this.roast = roast;
        this.origin = origin;
        this.acidity = acidity;
        this.pricePerPound = pricePerPound;
    }

    public BigDecimal getPricePerPound() {
        return pricePerPound;
    }

    public void setPricePerPound(BigDecimal pricePerPound) {
        this.pricePerPound = pricePerPound;
    }

    private BigDecimal pricePerPound;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeanType() {
        return beanType;
    }

    public void setBeanType(String beanType) {
        this.beanType = beanType;
    }

    public String getFermentation() {
        return fermentation;
    }

    public void setFermentation(String fermentation) {
        this.fermentation = fermentation;
    }

    public String getRoast() {
        return roast;
    }

    public void setRoast(String roast) {
        this.roast = roast;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public BigInteger getAcidity() {
        return acidity;
    }

    public void setAcidity(BigInteger acidity) {
        this.acidity = acidity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return Objects.equals(id, coffee.id) && Objects.equals(beanType, coffee.beanType) && Objects.equals(fermentation, coffee.fermentation) && Objects.equals(roast, coffee.roast) && Objects.equals(origin, coffee.origin) && Objects.equals(acidity, coffee.acidity) && Objects.equals(pricePerPound, coffee.pricePerPound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beanType, fermentation, roast, origin, acidity, pricePerPound);
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", beanType='" + beanType + '\'' +
                ", fermentation='" + fermentation + '\'' +
                ", roast='" + roast + '\'' +
                ", origin='" + origin + '\'' +
                ", acidity=" + acidity +
                ", pricePerPound=" + pricePerPound +
                '}';
    }
}
