package com.mockmvcmagic.testinginvalidinputs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockmvcmagic.testinginvalidinputs.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldAcceptProperlyFormattedProductPostRequest() throws Exception {
        Product inputProduct = new Product("Wham-O", "Happy Fun Ball", 250, 24.99);
        String inputProductJson = mapper.writeValueAsString(inputProduct);

        System.out.println("Sending this product to the post endpoint: " + inputProduct);
        System.out.println("But we have to send json: " + inputProductJson);
        mockMvc.perform(post("/products")
                .content(inputProductJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(inputProductJson));

    }

    @Test
    public void shouldRejectImproperlyFormattedProductPostRequest() throws Exception {
        Product inputProduct = new Product("Wham-O", "Happy Fun Ball", 250, 24.99);
        String inputProductJson = mapper.writeValueAsString(inputProduct);
        //  option 1 - replace the number with a json string
        inputProductJson = inputProductJson.replace("250", "\"A String \"");
        //  option 2 - build an entire json string by hand
        String kevinsManualString = "{\"brand\":\"Wham-O\",\"description\":\"Happy Fun Ball\",\"quantity\":\"A String \",\"price\":24.99}";
        //  option 3 - use a map to mimic the product structure
        Map<String, String> inputMap = new HashMap<>();
        inputMap.put("brand", "Wham-O");
        inputMap.put("description", "Hapy Fun Ball");
        inputMap.put("quantity", "this is not a number");
        inputMap.put("price", "123.12");
        String mapJson = mapper.writeValueAsString(inputMap);
        // option 4 - create a test class that is like a product, but with bad input types
        //   BadProduct would have all the same fields, but the wrong types - specifically, quantity would be a String
        BadProduct badProduct = new BadProduct("Amazon", "Ball", "lots of em", 44.22);
        String badProductJson = mapper.writeValueAsString(badProduct);


        System.out.println("Sending this product to the post endpoint: " + mapJson);
        System.out.println("But we have to send json: " + badProduct);
        mockMvc.perform(post("/products")
                        .content(badProductJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(inputProductJson));

    }

}