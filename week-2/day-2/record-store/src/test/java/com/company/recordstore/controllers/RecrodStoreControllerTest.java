package com.company.recordstore.controllers;

import com.company.recordstore.models.Record;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Based on the activity in class on 10/5
@RunWith(SpringRunner.class)
@WebMvcTest(RecrodStoreController.class)
public class RecrodStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        // we may need to set up some data in here
    }

    @Test
    public void shouldReturnAllRecordsInCollection() throws Exception {
            // Arrange and Act
            mockMvc.perform(get("/record"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0]").isNotEmpty());
    }

    @Test
    public void shouldReturnNewRecordOnPostRequest() throws Exception {
        Record inputRecord = new Record();
        inputRecord.setTitle("Rubber Soul");
        inputRecord.setArtist("The Beatles");

        String inputRecordJson = mapper.writeValueAsString(inputRecord);

        Record outputRecord = new Record();
        outputRecord.setTitle("Rubber Soul");
        outputRecord.setArtist("The Beatles");
        outputRecord.setId(6);

        String outputRecordJson = mapper.writeValueAsString(outputRecord);

        mockMvc.perform(post("/record")
                .content(inputRecordJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputRecordJson));
    }

    @Test
    public void shouldRespondWithNoContentWhenDeletingARecord() throws Exception {
        // Arrange and Act
        mockMvc.perform(delete("/record/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnOneRecordOnGetByIdRequestAndHaveStatusOk() throws Exception {
        Record outputRecord = new Record("Weezer", "Weezer (Blue)", 2);

        String outputRecordJson = mapper.writeValueAsString(outputRecord);

        mockMvc.perform(get("/record/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputRecordJson));
    }


    @Test
    public void shouldGiveANoContentHttpStatusWhenPutRecordIsRequested() throws Exception {
        Record inputRecord = new Record();
        inputRecord.setTitle("Rubber Soul");
        inputRecord.setArtist("The Beatles");

        String inputRecordJson = mapper.writeValueAsString(inputRecord);

        mockMvc.perform(put("/record/{id}", 4)
                        .content(inputRecordJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }



}