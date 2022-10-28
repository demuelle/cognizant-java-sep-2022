package com.trilogyed.gamestore.repository;

import com.trilogyed.gamestore.model.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtRepositoryTest {

    @Autowired
    TShirtRepository tShirtRepository;

    @Before
    public void setUp() throws Exception {
        tShirtRepository.deleteAll();
    }

    @Test
    public void shouldAddFindDeleteTShirt() {

        //Arrange
        TShirt newTShirt = new TShirt();
        newTShirt.setQuantity(1);
        newTShirt.setPrice( new BigDecimal("10.05"));
        newTShirt.setDescription("Everybody Knows Your Name");
        newTShirt.setColor("SkyBlue");
        newTShirt.setSize("M");

        //Act
        newTShirt = tShirtRepository.save(newTShirt);
        TShirt savedTShirt = tShirtRepository.save(newTShirt);

        //Assert
        assertEquals(newTShirt, savedTShirt);

        //Arrange
        newTShirt.setQuantity(5);
        newTShirt.setDescription("Aint nobody got time for that!");

        //Act
        tShirtRepository.save(newTShirt);
        savedTShirt = tShirtRepository.save(newTShirt);

        //Assert
        assertEquals(newTShirt, savedTShirt);

        //Act
        tShirtRepository.deleteById(newTShirt.getId());
        Optional<TShirt> foundTShirt = tShirtRepository.findById(newTShirt.getId());

        //Assert
        assertFalse(foundTShirt.isPresent());
    }

    @Test
    public void shouldFindAllTShirt(){
        //Arrange
        TShirt newTShirt1 = new TShirt();
        newTShirt1.setQuantity(1);
        newTShirt1.setPrice( new BigDecimal("10.05"));
        newTShirt1.setDescription("Everybody Knows Your Name");
        newTShirt1.setColor("SkyBlue");
        newTShirt1.setSize("M");

        TShirt newTShirt2 = new TShirt();
        newTShirt2.setQuantity(11);
        newTShirt2.setPrice( new BigDecimal("15.00"));
        newTShirt2.setDescription("I am not always right...");
        newTShirt2.setColor("Pink");
        newTShirt2.setSize("S");

        //Act
        newTShirt1 = tShirtRepository.save(newTShirt1);
        newTShirt2 = tShirtRepository.save(newTShirt2);
        List<TShirt> allTShirt = new ArrayList();
        allTShirt.add(newTShirt1);
        allTShirt.add(newTShirt2);

        //Act
        List<TShirt> foundAllTShirt = tShirtRepository.findAll();

        //Assert
        assertEquals(allTShirt.size(),foundAllTShirt.size());
    }

    @Test
    public void shouldFindTShirtByColor(){
        //Arrange
        TShirt newTShirt1 = new TShirt();
        newTShirt1.setQuantity(1);
        newTShirt1.setPrice( new BigDecimal("10.05"));
        newTShirt1.setDescription("Everybody Knows Your Name");
        newTShirt1.setColor("SkyBlue");
        newTShirt1.setSize("M");

        TShirt newTShirt2 = new TShirt();
        newTShirt2.setQuantity(11);
        newTShirt2.setPrice( new BigDecimal("15.00"));
        newTShirt2.setDescription("I am not always right...");
        newTShirt2.setColor("Pink");
        newTShirt2.setSize("S");

        TShirt newTShirt3 = new TShirt();
        newTShirt3.setQuantity(9);
        newTShirt3.setPrice( new BigDecimal("19.00"));
        newTShirt3.setDescription("Trust me I am a Pro...crastinator");
        newTShirt3.setColor("Pink");
        newTShirt3.setSize("L");

        //Act
        newTShirt1 = tShirtRepository.save(newTShirt1);
        newTShirt2 = tShirtRepository.save(newTShirt2);
        newTShirt3 = tShirtRepository.save(newTShirt3);

        //Act
        List<TShirt> foundNoTShirt = tShirtRepository.findAllByColor("InvalidValue");

        List<TShirt> foundOneTShirt = tShirtRepository.findAllByColor("SkyBlue");

        List<TShirt> foundTwoTShirt = tShirtRepository.findAllByColor("Pink");

        //Assert
        assertEquals(foundNoTShirt.size(),0);
        assertEquals(foundOneTShirt.size(),1);
        assertEquals(foundTwoTShirt.size(),2);

    }
    @Test
    public void shouldFindTShirtBySize(){
        //Arrange
        TShirt newTShirt1 = new TShirt();
        newTShirt1.setQuantity(1);
        newTShirt1.setPrice( new BigDecimal("10.05"));
        newTShirt1.setDescription("Everybody Knows Your Name");
        newTShirt1.setColor("SkyBlue");
        newTShirt1.setSize("L");

        TShirt newTShirt2 = new TShirt();
        newTShirt2.setQuantity(11);
        newTShirt2.setPrice( new BigDecimal("15.00"));
        newTShirt2.setDescription("I am not always right...");
        newTShirt2.setColor("Pink");
        newTShirt2.setSize("S");

        TShirt newTShirt3 = new TShirt();
        newTShirt3.setQuantity(9);
        newTShirt3.setPrice( new BigDecimal("19.00"));
        newTShirt3.setDescription("Trust me I am a Pro...crastinator");
        newTShirt3.setColor("Pink");
        newTShirt3.setSize("L");

        //Act
        newTShirt1 = tShirtRepository.save(newTShirt1);
        newTShirt2 = tShirtRepository.save(newTShirt2);
        newTShirt3 = tShirtRepository.save(newTShirt3);

        //Act
        List<TShirt> foundNoTShirt = tShirtRepository.findAllBySize("InvalidValue");

        List<TShirt> foundOneTShirt = tShirtRepository.findAllBySize("S");

        List<TShirt> foundTwoTShirt = tShirtRepository.findAllBySize("L");

        //Assert
        assertEquals(foundNoTShirt.size(),0);
        assertEquals(foundOneTShirt.size(),1);
        assertEquals(foundTwoTShirt.size(),2);
    }
}