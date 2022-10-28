package com.trilogyed.gamestore.viewModel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {

    private long id;

    @NotEmpty(message = "Title of the game is required.")
    private String title;

    @NotEmpty(message = "ESRB Rating of the game is required.")
    private String esrbRating;

    @NotEmpty(message = "Description of the game is required.")
    private String description;

    @NotNull(message = "Price of the game is required.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Min Price for this item is $0.0")
    @DecimalMax(value = "999.99", inclusive = true, message = "Max Price is $999.99")
    private BigDecimal price;

    @NotEmpty(message = "Studio of the game is required.")
    private String studio;

    @NotNull(message = "Game quantity is required")
    @Min(value = 1, message = "Min Quantity is 1")
    @Max(value = 50000, message = "Max Quantity is 50,000")
    private long quantity;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (o == null || getClass() != o.getClass()) return false;
        GameViewModel that = (GameViewModel) o;
        return getId() == that.getId() &&
                getQuantity() == that.getQuantity() &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getEsrbRating(), that.getEsrbRating()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getStudio(), that.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getEsrbRating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }
}
