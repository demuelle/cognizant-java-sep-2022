package org.coffee.coffeeordering;

import org.coffee.coffeeordering.model.Coffee;
import org.coffee.coffeeordering.respository.CoffeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    @Test
    public void shouldFilterByRoast() {
        // arrange
        Coffee coffee1 = new Coffee(null, "arabica", "cat", "medium", "Kenya", new BigInteger("5"), new BigDecimal("11.99"));
        Coffee coffee2 = new Coffee(null, "string", "buffalo", "dark", "Ethiopia", new BigInteger("3"), new BigDecimal("13.99"));
        Coffee coffee3 = new Coffee(null, "lima", "giraffe", "medium", "Iraq", new BigInteger("4"), new BigDecimal("12.99"));

        coffee1 = repo.save(coffee1);
        coffee2 = repo.save(coffee2);
        coffee3 = repo.save(coffee3);

        // act
        List<Coffee> mediumRoastList = repo.findByRoast("medium");

        // assert - maybe turn these into 2 sets and then compare (we don't have a guaranteed order
        // on the query we wrote, but sets just confirm that every item is present in both)
        assertEquals(new HashSet<Coffee>(Arrays.asList(coffee3, coffee1)), new HashSet<Coffee>(mediumRoastList));

        List<Coffee> darkRoastList = repo.findByRoast("dark");
        assertEquals(new HashSet<Coffee>(Arrays.asList(coffee2)), new HashSet<Coffee>(darkRoastList));

        List<Coffee> lightRoastList = repo.findByRoast("light");
        assertEquals(0, lightRoastList.size());
    }
    // arrange
    // build the whatIExpect variable (and parameters)
    // act
    // generate the whatIGot variable (by calling a method)
    // assert
    // assertEquals(whatIExpect, whatIGot)

}