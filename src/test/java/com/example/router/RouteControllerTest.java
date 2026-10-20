package com.example.router;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenCardMessage_whenPostRoute_thenCardsTeam() throws Exception {
        mockMvc.perform(post("/api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"My card was declined\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category").value("CARDS"))
                .andExpect(jsonPath("$.team").value("cards-team@bank"));
    }

    @Test
    void givenBlankMessage_whenPostRoute_thenBadRequest() throws Exception {
        mockMvc.perform(post("/api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"   \"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("empty message"));
    }

    @Test
    void givenMissingMessage_whenPostRoute_thenBadRequest() throws Exception {
        mockMvc.perform(post("/api/route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("null message"));
    }
}
