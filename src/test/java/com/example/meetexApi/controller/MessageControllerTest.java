package com.example.meetexApi.controller;

import com.example.meetexApi.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    @WithMockUser(username = "testUser")
    public void testSendMessage() throws Exception {
        mockMvc.perform(post("/api/users/1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"recipientId\": 2, \"content\": \"Test message\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllMessages() throws Exception {
        mockMvc.perform(get("/api/users/1/messages")
                        .param("recipientId", "2"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testDeleteMessage() throws Exception {
        mockMvc.perform(delete("/api/messages/1"))
                .andExpect(status().isOk());
    }
}
