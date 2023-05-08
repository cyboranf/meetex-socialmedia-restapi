package com.example.meetexApi.controller;

import com.example.meetexApi.service.PostService;
import com.example.meetexApi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;

    @Test
    public void testCreatePost() throws Exception {
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Title\", \"text\": \"Test Text\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetPostById() throws Exception {
        mockMvc.perform(get("/api/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePost() throws Exception {
        mockMvc.perform(put("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Title\", \"text\": \"Updated Text\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePost() throws Exception {
        mockMvc.perform(delete("/api/posts/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLikePost() throws Exception {
        mockMvc.perform(post("/api/posts/1/like"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUnlikePost() throws Exception {
        mockMvc.perform(delete("/api/posts/1/unlike"))
                .andExpect(status().isOk());
    }
}
