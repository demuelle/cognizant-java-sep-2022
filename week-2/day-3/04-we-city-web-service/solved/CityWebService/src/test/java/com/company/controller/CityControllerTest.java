package com.company.controller;

import com.company.model.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldGet204HttpResponseOnPut() throws Exception {
        // Arrange
        City inputCity = new City("Denver", "Colorado", 801001, true);

        String inputCityJson = mapper.writeValueAsString(inputCity);

        mockMvc.perform(put("/city/{name}", "Denver")       // Act
                .content(inputCityJson)                     // Act
                .contentType(MediaType.APPLICATION_JSON)    // Act
        ).andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGet404HttpResponseOnPutWhenCityDoesNotExist() throws Exception {
        // Arrange
        City inputCity = new City("Tucson", "Arizona", 432543, false);

        String inputCityJson = mapper.writeValueAsString(inputCity);

        mockMvc.perform(put("/city/{name}", "Tucson")       // Act
                        .content(inputCityJson)                     // Act
                        .contentType(MediaType.APPLICATION_JSON)    // Act
                ).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNewCityOnValidPostRequest() throws Exception {
        // Since the cities do not have ID's, we can use the same Object to create both the input and the output json.
        City city = new City("Fort Lauderdale", "Florida", 183109, false);

        String inputJson = mapper.writeValueAsString(city);
        String outputJson = mapper.writeValueAsString(city);

        mockMvc.perform(
                post("/city")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyName() throws Exception {
        City city = new City("", "Florida", 183109, false);

        String inputJson = mapper.writeValueAsString(city);

        mockMvc.perform(
                post("/city")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAnEmptyState() throws Exception {
        City city = new City("Fort Lauderdale", "", 183109, false);

        String inputJson = mapper.writeValueAsString(city);

        mockMvc.perform(
                post("/city")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturn422WhenPostingAPopulationLessThanOne() throws Exception {
        City city = new City("Fort Lauderdale", "Florida", 0, false);

        String inputJson = mapper.writeValueAsString(city);

        mockMvc.perform(
                post("/city")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldDeleteByCityAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/city/New York City"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturn404WhenAttemptingToDeleteACityThatDoesNotExist() throws Exception {
        mockMvc.perform(delete("/city/New Donk City"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnAllCitiesInCollection() throws Exception {
        mockMvc.perform(get("/city"))
                .andDo(print())
                .andExpect(status().isOk())

                // ASSERT that the JSON array is present and not empty. We will test GET all endpoints deeper in the
                // future but this is good enough for now.
                .andExpect(jsonPath("$[0]").isNotEmpty());
    }

    @Test
    public void shouldReturnCityByName() throws Exception {
        // Since the cities do not have ID's, we can use the same Object to create both the input and the output json.
        City city = new City("Sacramento", "California", 525398, true);

        String outputJson = mapper.writeValueAsString(city);

        mockMvc.perform(get("/city/sacramento"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn404StatusCodeIfCityNotFound() throws Exception {
        mockMvc.perform(get("/city/New Donk City"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
