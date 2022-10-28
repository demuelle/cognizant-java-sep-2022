package com.trilogyed.gamestore.repository;

import com.trilogyed.gamestore.model.Console;
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
public class ConsoleRepositoryTest {

    @Autowired
    ConsoleRepository consoleRepository;

    @org.junit.Before
    public void setUp() throws Exception {
        consoleRepository.deleteAll();
    }

    @Test
    public void shouldAddFindDeleteConsole() {

        //Arrange
        Console newCon = new Console();
        newCon.setQuantity(1);
        newCon.setPrice( new BigDecimal("10.05"));
        newCon.setProcessor("AMD");
        newCon.setMemoryAmount("2GB");
        newCon.setManufacturer("Sega");
        newCon.setModel("P3");

        //Act
        newCon = consoleRepository.save(newCon);
        Optional<Console> foundCon = consoleRepository.findById(newCon.getId());

        //Assert
        assertTrue(foundCon.isPresent());
        assertEquals(newCon, foundCon.get());

        //Arrange
        newCon.setQuantity(5);
        newCon.setProcessor("Intl");

        //Act
        consoleRepository.save(newCon);
        foundCon = consoleRepository.findById(newCon.getId());

        //Assert
        assertTrue(foundCon.isPresent());
        assertEquals(newCon, foundCon.get());

        //Act
        consoleRepository.deleteById(newCon.getId());
        foundCon = consoleRepository.findById(newCon.getId());

        //Assert
        assertFalse(foundCon.isPresent());
    }

    @Test
    public void shouldFindAllConsole(){
        //Arrange
        Console newCon1 = new Console();
        newCon1.setQuantity(1);
        newCon1.setPrice( new BigDecimal("10.05"));
        newCon1.setProcessor("AMD");
        newCon1.setMemoryAmount("2GB");
        newCon1.setManufacturer("Sega");
        newCon1.setModel("P3");

        Console newCon2 = new Console();
        newCon2.setQuantity(5);
        newCon2.setPrice( new BigDecimal("11.05"));
        newCon2.setProcessor("AMD");
        newCon2.setMemoryAmount("2GB");
        newCon2.setManufacturer("PS-IV");
        newCon2.setModel("P3");

        //Act
        newCon1 = consoleRepository.save(newCon1);
        newCon2 = consoleRepository.save(newCon2);
        List<Console> allConsole = new ArrayList();
        allConsole.add(newCon1);
        allConsole.add(newCon2);

        //Act
        List<Console> foundAllConsole = consoleRepository.findAll();

        //Assert
        assertEquals(allConsole.size(),foundAllConsole.size());
    }

    @Test
    public void shouldFindConsoleByManufacturer(){
        //Arrange
        Console newCon1 = new Console();
        newCon1.setQuantity(1);
        newCon1.setPrice( new BigDecimal("10.05"));
        newCon1.setProcessor("AMD");
        newCon1.setMemoryAmount("2GB");
        newCon1.setManufacturer("Sega");
        newCon1.setModel("P3");

        Console newCon2 = new Console();
        newCon2.setQuantity(5);
        newCon2.setPrice( new BigDecimal("11.05"));
        newCon2.setProcessor("AMD");
        newCon2.setMemoryAmount("2GB");
        newCon2.setManufacturer("PS-IV");
        newCon2.setModel("P3");

        Console newCon3 = new Console();
        newCon3.setQuantity(5);
        newCon3.setPrice( new BigDecimal("41.05"));
        newCon3.setProcessor("Intel");
        newCon3.setMemoryAmount("8GB");
        newCon3.setManufacturer("PS-IV");
        newCon3.setModel("P3");

        //Act
        newCon1 = consoleRepository.save(newCon1);
        newCon2 = consoleRepository.save(newCon2);
        newCon3 = consoleRepository.save(newCon3);

        //Act
        List<Console> foundNoConsole = consoleRepository.findAllByManufacturer("InvalidManufacturer");

        List<Console> foundOneConsole = consoleRepository.findAllByManufacturer("Sega");

        List<Console> foundTwoConsole = consoleRepository.findAllByManufacturer("PS-IV");

        //Assert
        assertEquals(foundNoConsole.size(),0);
        assertEquals(foundOneConsole.size(),1);
        assertEquals(foundTwoConsole.size(),2);
    }
}