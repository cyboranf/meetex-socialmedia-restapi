package com.example.meetexApi.controller;

import com.example.meetexApi.dto.community.CommunityRequestDTO;
import com.example.meetexApi.dto.community.MemberRequestDTO;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.CommunityService;
import com.example.meetexApi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommunityController.class)
public class CommunityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "testUser")
    public void testCreateCommunity() throws Exception {
        CommunityRequestDTO communityRequestDTO = new CommunityRequestDTO();
        communityRequestDTO.setName("Test community");

        mockMvc.perform(post("/api/communities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test community\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCommunityById() throws Exception {
        mockMvc.perform(get("/api/communities/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testUpdateCommunity() throws Exception {
        when(userService.findUserByUserName("testUser")).thenReturn(new User());

        mockMvc.perform(put("/api/communities/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated community\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testDeleteCommunity() throws Exception {
        when(userService.findUserByUserName("testUser")).thenReturn(new User());

        mockMvc.perform(delete("/api/communities/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testAddMember() throws Exception {
        when(userService.findUserByUserName("testUser")).thenReturn(new User());

        mockMvc.perform(post("/api/communities/1/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userId\": 2}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testRemoveMember() throws Exception {
        mockMvc.perform(delete("/api/communities/1/members/2"))
                .andExpect(status().isOk());
    }
}
