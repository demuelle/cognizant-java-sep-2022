package com.trilogyed.gamestore.controller;

import com.trilogyed.gamestore.service.GameStoreServiceLayer;
import com.trilogyed.gamestore.viewModel.TShirtViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TShirtController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // The aim of this unit test is to test the controller and NOT the service layer.
    // Therefore mock the service layer.
    @MockBean
    private GameStoreServiceLayer storeServiceLayer;

    @Autowired
    //used to move between Objects and JSON
    private ObjectMapper mapper;

    @Test
    public void shouldCreateTShirt() throws Exception{
        //Object to JSON in String
        String outputJson = null;
        String inputJson=null;

        //Arrange
        TShirtViewModel inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        TShirtViewModel savedTShirtViewModel = new TShirtViewModel();
        savedTShirtViewModel.setQuantity(1);
        savedTShirtViewModel.setPrice( new BigDecimal("10.05"));
        savedTShirtViewModel.setDescription("Everybody Knows Your Name");
        savedTShirtViewModel.setColor("SkyBlue");
        savedTShirtViewModel.setSize("M");
        savedTShirtViewModel.setId(51);

        outputJson = mapper.writeValueAsString(savedTShirtViewModel);

        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(savedTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnTShirtInfo() throws Exception{

        //Object to JSON in String
        String outputJson = null;

        //Arrange
        TShirtViewModel savedTShirtViewModel = new TShirtViewModel();
        savedTShirtViewModel.setQuantity(1);
        savedTShirtViewModel.setPrice( new BigDecimal("10.05"));
        savedTShirtViewModel.setDescription("Everybody Knows Your Name");
        savedTShirtViewModel.setColor("SkyBlue");
        savedTShirtViewModel.setSize("M");
        savedTShirtViewModel.setId(51);

        outputJson = mapper.writeValueAsString(savedTShirtViewModel);

        // Mocking DAO response
        // This is another way to mock using mockito
        // same as doReturn(gameViewModel).when(storeServiceLayer).getGame(8);
        // We could also set up our mocks in a separate method, if we so chose
        when(storeServiceLayer.getTShirt(51)).thenReturn(savedTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(get("/tshirt/{id}", 51))
                .andDo(print())
                .andExpect(status().isOk())
                //use the objectmapper output with the json method
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldUpdateTShirt() throws Exception{
        //Object to JSON in String
        String inputJson=null;

        //Arrange
        TShirtViewModel inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");
        inTShirtViewModel.setId(51);

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        TShirtViewModel savedTShirtViewModel = new TShirtViewModel();
        savedTShirtViewModel.setQuantity(18);
        savedTShirtViewModel.setPrice( new BigDecimal("15.05"));
        savedTShirtViewModel.setDescription("Everybody Knows Your Name");
        savedTShirtViewModel.setColor("SkyBlue");
        savedTShirtViewModel.setSize("M");
        savedTShirtViewModel.setId(51);


        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTShirt() throws Exception{
        //Object to JSON in String
        String inputJson=null;

        //Arrange
        TShirtViewModel inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");
        inTShirtViewModel.setId(51);

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).deleteTShirt(51);

        //Act & Assert
        this.mockMvc.perform(delete("/tshirt/{id}",51))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetTShirtByColor() throws Exception{
        //Object to JSON in String
        String outputJson = null;

        //Arrange
        TShirtViewModel savedTShirtViewModel1 = new TShirtViewModel();
        savedTShirtViewModel1.setQuantity(1);
        savedTShirtViewModel1.setPrice( new BigDecimal("10.05"));
        savedTShirtViewModel1.setDescription("Everybody Knows Your Name");
        savedTShirtViewModel1.setColor("SkyBlue");
        savedTShirtViewModel1.setSize("L");
        savedTShirtViewModel1.setId(51);

        TShirtViewModel savedTShirtViewModel2 = new TShirtViewModel();
        savedTShirtViewModel2.setQuantity(9);
        savedTShirtViewModel2.setPrice( new BigDecimal("20.05"));
        savedTShirtViewModel2.setDescription("Everybody Loves Rayman");
        savedTShirtViewModel2.setColor("SkyBlue");
        savedTShirtViewModel2.setSize("M");
        savedTShirtViewModel2.setId(61);

        TShirtViewModel savedTShirtViewModel3 = new TShirtViewModel();
        savedTShirtViewModel3.setQuantity(17);
        savedTShirtViewModel3.setPrice( new BigDecimal("15.05"));
        savedTShirtViewModel3.setDescription("Bart Simpson");
        savedTShirtViewModel3.setColor("Yellow");
        savedTShirtViewModel3.setSize("S");
        savedTShirtViewModel3.setId(88);

        List<TShirtViewModel> foundList = new ArrayList();
        foundList.add(savedTShirtViewModel1);
        foundList.add(savedTShirtViewModel2);


        outputJson = mapper.writeValueAsString(foundList);

        //Mock call to service layer...
        when(storeServiceLayer.getTShirtByColor("SkyBlue")).thenReturn(foundList);

        //Act & Assert
        this.mockMvc.perform(get("/tshirt/color/{color}","SkyBlue"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getTShirtByColor("non-existent color")).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/tshirt/color/{color}","non-existent color"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetTShirtBySize() throws Exception{
        //Object to JSON in String
        String outputJson = null;

        //Arrange
        TShirtViewModel savedTShirtViewModel1 = new TShirtViewModel();
        savedTShirtViewModel1.setQuantity(1);
        savedTShirtViewModel1.setPrice( new BigDecimal("10.05"));
        savedTShirtViewModel1.setDescription("Everybody Knows Your Name");
        savedTShirtViewModel1.setColor("SkyBlue");
        savedTShirtViewModel1.setSize("L");
        savedTShirtViewModel1.setId(51);

        TShirtViewModel savedTShirtViewModel2 = new TShirtViewModel();
        savedTShirtViewModel2.setQuantity(9);
        savedTShirtViewModel2.setPrice( new BigDecimal("20.05"));
        savedTShirtViewModel2.setDescription("Everybody Loves Rayman");
        savedTShirtViewModel2.setColor("SkyBlue");
        savedTShirtViewModel2.setSize("M");
        savedTShirtViewModel2.setId(61);

        TShirtViewModel savedTShirtViewModel3 = new TShirtViewModel();
        savedTShirtViewModel3.setQuantity(17);
        savedTShirtViewModel3.setPrice( new BigDecimal("15.05"));
        savedTShirtViewModel3.setDescription("Bart Simpson");
        savedTShirtViewModel3.setColor("Yellow");
        savedTShirtViewModel3.setSize("S");
        savedTShirtViewModel3.setId(88);

        List<TShirtViewModel> foundList = new ArrayList();
        foundList.add(savedTShirtViewModel3);


        outputJson = mapper.writeValueAsString(foundList);

        //Mock call to service layer...
        when(storeServiceLayer.getTShirtBySize("S")).thenReturn(foundList);

        //Act & Assert
        this.mockMvc.perform(get("/tshirt/size/{size}","S"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getTShirtBySize("XL")).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/tshirt/size/{size}","XL"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldGetAllTShirt() throws Exception{
        //Object to JSON in String
        String outputJson = null;

        //Arrange
        TShirtViewModel savedTShirtViewModel1 = new TShirtViewModel();
        savedTShirtViewModel1.setQuantity(1);
        savedTShirtViewModel1.setPrice( new BigDecimal("10.05"));
        savedTShirtViewModel1.setDescription("Everybody Knows Your Name");
        savedTShirtViewModel1.setColor("SkyBlue");
        savedTShirtViewModel1.setSize("L");
        savedTShirtViewModel1.setId(51);

        TShirtViewModel savedTShirtViewModel2 = new TShirtViewModel();
        savedTShirtViewModel2.setQuantity(9);
        savedTShirtViewModel2.setPrice( new BigDecimal("20.05"));
        savedTShirtViewModel2.setDescription("Everybody Loves Rayman");
        savedTShirtViewModel2.setColor("SkyBlue");
        savedTShirtViewModel2.setSize("M");
        savedTShirtViewModel2.setId(61);

        TShirtViewModel savedTShirtViewModel3 = new TShirtViewModel();
        savedTShirtViewModel3.setQuantity(17);
        savedTShirtViewModel3.setPrice( new BigDecimal("15.05"));
        savedTShirtViewModel3.setDescription("Bart Simpson");
        savedTShirtViewModel3.setColor("Yellow");
        savedTShirtViewModel3.setSize("S");
        savedTShirtViewModel3.setId(88);

        List<TShirtViewModel> foundList = new ArrayList();
        foundList.add(savedTShirtViewModel3);


        outputJson = mapper.writeValueAsString(foundList);

        //Mock call to service layer...
        when(storeServiceLayer.getAllTShirts()).thenReturn(foundList);

        //Act & Assert
        this.mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

        //Mock call to service layer...
        when(storeServiceLayer.getAllTShirts()).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(get("/tshirt"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    //test for bad incoming data...
    @Test
    public void shouldFailCreateTShirtWithBadData() throws Exception{
        //Object to JSON in String
        String outputJson = null;
        String inputJson=null;

        //Arrange
        TShirtViewModel inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize(null);

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())

                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor(null);
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription(null);
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("-1.00"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("1000.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( null);
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(-1);
        inTShirtViewModel.setPrice( new BigDecimal("1000.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(70000);
        inTShirtViewModel.setPrice( new BigDecimal("1000.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        when(storeServiceLayer.createTShirt(inTShirtViewModel)).thenReturn(null);

        //Act & Assert
        this.mockMvc.perform(post("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldFailUpdateTShirtWithBadData() throws Exception{
        //Object to JSON in String
        String outputJson = null;
        String inputJson=null;

        //Arrange
        TShirtViewModel inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("");
        inTShirtViewModel.setId(31);

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize(null);
        inTShirtViewModel.setId(31);

        inputJson = mapper.writeValueAsString(inTShirtViewModel);


        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor(null);
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription("");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("10.05"));
        inTShirtViewModel.setDescription(null);
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("-1.00"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( new BigDecimal("1000.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(1);
        inTShirtViewModel.setPrice( null);
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Mock call to service layer...
        //Nothing to mock!
        //Checking checking for the correct response status code
        doNothing().when(storeServiceLayer).updateTShirt(inTShirtViewModel);

        //Act & Assert
        this.mockMvc.perform(put("/tshirt")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());


        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(-1);
        inTShirtViewModel.setPrice( new BigDecimal("1000.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

        //Arrange
        inTShirtViewModel = new TShirtViewModel();
        inTShirtViewModel.setQuantity(70000);
        inTShirtViewModel.setPrice( new BigDecimal("1000.05"));
        inTShirtViewModel.setDescription("Everybody Knows Your Name");
        inTShirtViewModel.setColor("SkyBlue");
        inTShirtViewModel.setSize("M");

        inputJson = mapper.writeValueAsString(inTShirtViewModel);

    }

}