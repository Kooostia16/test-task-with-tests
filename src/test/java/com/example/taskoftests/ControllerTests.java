package com.example.taskoftests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCountOfUniqueUsers() throws Exception {
        mockMvc.perform(get("/count").accept(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{count: 10}"));
    }

    @Test
    public void testPutUserWithPositiveValue() throws Exception {
        mockMvc.perform(put("/change-user").param("userToModify", "2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPutUserWithNegativeValue() throws Exception {
        mockMvc.perform(put("/change-user").param("userToModify", "-2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}