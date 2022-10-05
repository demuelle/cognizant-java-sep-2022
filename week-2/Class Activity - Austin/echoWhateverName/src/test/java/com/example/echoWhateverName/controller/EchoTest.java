package com.example.echoWhateverName.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(Echo.class)
public class EchoTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper myObjectMapper = new ObjectMapper();



    @Test
    public void overlyComplicatedTestNameMadeByAustin() throws Exception{
        // arrange and act
        mockMvc.perform(get("/echo/{input}","string1"))
                .andDo(print())
                .andExpect(status().isOk()) // Assert 1
                .andExpect(content().string("string1")); //Assert 2
    }
}
