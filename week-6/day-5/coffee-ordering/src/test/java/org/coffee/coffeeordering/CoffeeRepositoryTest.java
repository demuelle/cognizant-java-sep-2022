package org.coffee.coffeeordering;

import org.coffee.coffeeordering.model.Coffee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeRepositoryTest {

    @Autowired
    private CoffeeRepository repo;

    @Before
    public void setUp() {
        // clear out database
        repo.deleteAll();
    }

    @Test
    public void shouldCreateGetAndDeleteCoffee() {
        Coffee coffee = new Coffee();
        coffee.setBeanType("robosto");
        coffee.setFermentation("elephant");
        coffee.setRoast("medium");
        coffee.setOrigin("Kenya");
        coffee.setAcidity(new BigInteger("5"));

        coffee.setPricePerPound(new BigDecimal("11.99"));

        coffee = repo.save(coffee);

        Coffee whatIGot = repo.findById(coffee.getId()).get();

        assertEquals(coffee, whatIGot);

        repo.deleteById(coffee.getId());

        Optional<Coffee> shouldBeEmptyOptional = repo.findById(coffee.getId());
        assertEquals(false, shouldBeEmptyOptional.isPresent());
    }

    // arrange
    // build the whatIExpect variable (and parameters)
    // act
    // generate the whatIGot variable (by calling a method)
    // assert
    // assertEquals(whatIExpect, whatIGot)

}