package com.example.meetexApi.controller;

import com.example.meetexApi.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NotificationController.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    public void testGetAllNotifications() throws Exception {
        mockMvc.perform(get("/api/users/1/notifications"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNotification() throws Exception {
        mockMvc.perform(put("/api/notifications/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"read\": true}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNotification() throws Exception {
        mockMvc.perform(delete("/api/notifications/1"))
                .andExpect(status().isNoContent());
    }
}
