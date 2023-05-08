package com.example.meetexApi.controller;

import com.example.meetexApi.dto.comment.CommentRequestDTO;
import com.example.meetexApi.model.Comment;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.CommentService;
import com.example.meetexApi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "testUser")
    public void testCreateComment() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setText("Test comment");

        // Define behavior of the mock services
        when(userService.findUserByUserName("testUser")).thenReturn(new User());
        when(commentService.createComment(anyLong(), any(CommentRequestDTO.class), any(User.class))).thenReturn(new Comment());

        // Perform a POST request and assert the expected response
        mockMvc.perform(post("/api/posts/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\": \"Test comment\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetAllCommentsForPost() throws Exception {
        // Perform a GET request and assert the expected response
        mockMvc.perform(get("/api/posts/1/comments"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testUpdateComment() throws Exception {
        // Perform a PUT request and assert the expected response
        mockMvc.perform(put("/api/comments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\": \"Updated comment\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testUser")
    public void testDeleteComment() throws Exception {
        // Perform a DELETE request and assert the expected response
        mockMvc.perform(delete("/api/comments/1"))
                .andExpect(status().isOk());
    }

}
