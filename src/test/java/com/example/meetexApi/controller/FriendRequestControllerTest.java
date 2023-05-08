package com.example.meetexApi.controller;

import com.example.meetexApi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FriendRequestController.class)
public class FriendRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "testUser")
    public void testSendFriendRequest() throws Exception {
        mockMvc.perform(post("/api/users/1/friend-requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"senderId\": 1, \"receiverId\": 2}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFriendRequests() throws Exception {
        mockMvc.perform(get("/api/users/1/friend-requests"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testAcceptOrDeclineFriendRequest() throws Exception {
        mockMvc.perform(put("/api/users/1/accept-friend-request/2")
                        .param("action", "accept"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/users/1/accept-friend-request/2")
                        .param("action", "decline"))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/users/1/accept-friend-request/2")
                        .param("action", "invalid"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testDeleteFriendRequest() throws Exception {
        mockMvc.perform(delete("/api/users/1/delete-friend-request/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllFriends() throws Exception {
        mockMvc.perform(get("/api/users/1/friends"))
                .andExpect(status().isOk());
    }
}
