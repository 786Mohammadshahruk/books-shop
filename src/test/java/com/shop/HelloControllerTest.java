package com.shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
@AutoConfigureMockMvc //it tells have to create mockMvc object
public class HelloControllerTest {

    @Autowired
    private transient MockMvc mockMvc; //inject the mock


    @Test
    void shouldReturnHelloWithOkResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")).andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }
}
