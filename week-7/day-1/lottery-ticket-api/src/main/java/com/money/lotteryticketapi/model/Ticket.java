package com.money.lotteryticketapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
@Table(name="ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String numbers;
    private Integer extraBallNumber;
    private Boolean multiplier;


    public Ticket(){}

    public Ticket(String numbers, Integer extraBallNumber, Boolean multiplier) {
        this(null, numbers, extraBallNumber, multiplier);
    }

    public Ticket(Long id, String numbers, Integer extraBallNumber, Boolean multiplier) {
        this.id = id;
        this.numbers = numbers;
        this.extraBallNumber = extraBallNumber;
        this.multiplier = multiplier;
    }


    // TODO: LocalDate drawDate
    // TODO: LocalDateTime generatedDate


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public Integer getExtraBallNumber() {
        return extraBallNumber;
    }

    public void setExtraBallNumber(Integer extraBallNumber) {
        this.extraBallNumber = extraBallNumber;
    }

    public Boolean getMultiplier() {
        return multiplier;
    }

    // turn a hyphen delimited string of numbers into a list of integers
    // if numbers is 4-15-22-33-59, return the list (4, 15, 22, 33, 59)
    public List<Integer> parseNumbers() {
        if (numbers == null) return new ArrayList<>();
        return Arrays.asList(numbers.split("-")).stream().map(x -> new Integer(x)).collect(Collectors.toList());
    }

    // turn a hyphen delimited string of numbers into a list of integers
    // if numbers is 4-15-22-33-59, return the list (4, 15, 22, 33, 59)
    public void storeNumbers(List<Integer> numberList) {
        this.numbers = "";
        if (numberList != null && numberList.size() > 0) {
            this.numbers += numberList.get(0);
            int index = 0;
            while (++index < numberList.size()) {
                this.numbers += "-" + numberList.get(index);
            }
        }
    }



    public void setMultiplier(Boolean multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id) && Objects.equals(numbers, ticket.numbers) && Objects.equals(extraBallNumber, ticket.extraBallNumber) && Objects.equals(multiplier, ticket.multiplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numbers, extraBallNumber, multiplier);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", numbers='" + numbers + '\'' +
                ", extraBallNumber=" + extraBallNumber +
                ", multiplier=" + multiplier +
                '}';
    }
}
