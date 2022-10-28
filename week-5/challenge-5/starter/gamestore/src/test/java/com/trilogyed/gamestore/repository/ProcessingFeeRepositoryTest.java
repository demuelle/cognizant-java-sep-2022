package com.trilogyed.gamestore.repository;

import com.trilogyed.gamestore.model.ProcessingFee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeRepositoryTest {

    @Autowired
    ProcessingFeeRepository processingFeeRepository;

    @org.junit.Before
    public void setUp() throws Exception {
        processingFeeRepository.deleteAll();
    }

    @Test
    public void getProcessingFee() {
        // Arrange
        ProcessingFee tShirtProcessingFee = new ProcessingFee();
        tShirtProcessingFee.setProductType("T-Shirts");
        tShirtProcessingFee.setFee(new BigDecimal("1.98"));

        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setProductType("Consoles");
        consoleProcessingFee.setFee(new BigDecimal("14.99"));

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setProductType("Games");
        gameProcessingFee.setFee(new BigDecimal("1.49"));

        // Act
        processingFeeRepository.save(tShirtProcessingFee);
        processingFeeRepository.save(consoleProcessingFee);
        processingFeeRepository.save(gameProcessingFee);

        // Assert
        Optional<ProcessingFee> foundFee;

        foundFee = processingFeeRepository.findById("T-Shirts");
        assertTrue(foundFee.isPresent());
        assertEquals(foundFee.get().getFee(), new BigDecimal("1.98"));

        foundFee = processingFeeRepository.findById("Consoles");
        assertTrue(foundFee.isPresent());
        assertEquals(foundFee.get().getFee(), new BigDecimal("14.99"));

        foundFee = processingFeeRepository.findById("Games");
        assertTrue(foundFee.isPresent());
        assertEquals(foundFee.get().getFee(), new BigDecimal("1.49"));
    }

    @Test
    public void getProcessingFeeObject() {
        // Arrange
        ProcessingFee tShirtProcessingFee = new ProcessingFee();
        tShirtProcessingFee.setProductType("T-Shirts");
        tShirtProcessingFee.setFee(new BigDecimal("1.98"));

        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setProductType("Consoles");
        consoleProcessingFee.setFee(new BigDecimal("14.99"));

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setProductType("Games");
        gameProcessingFee.setFee(new BigDecimal("1.49"));

        // Act
        processingFeeRepository.save(tShirtProcessingFee);
        processingFeeRepository.save(consoleProcessingFee);
        processingFeeRepository.save(gameProcessingFee);

        // Assert
        Optional<ProcessingFee> foundProcessingFee = processingFeeRepository.findById(tShirtProcessingFee.getProductType());
        assertTrue(foundProcessingFee.isPresent());
        assertEquals(tShirtProcessingFee, foundProcessingFee.get());

        foundProcessingFee = processingFeeRepository.findById(consoleProcessingFee.getProductType());
        assertTrue(foundProcessingFee.isPresent());
        assertEquals(consoleProcessingFee, foundProcessingFee.get());

        foundProcessingFee = processingFeeRepository.findById(gameProcessingFee.getProductType());
        assertTrue(foundProcessingFee.isPresent());
        assertEquals(gameProcessingFee,foundProcessingFee.get());
    }
}
