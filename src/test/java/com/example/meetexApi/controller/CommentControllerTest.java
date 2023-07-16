package com.example.meetexApi.controller;

import com.example.meetexApi.dto.comment.CommentRequestDTO;
import com.example.meetexApi.dto.comment.CommentResponseDTO;
import com.example.meetexApi.dto.comment.CommentUpdateRequestDTO;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.CommentService;
import com.example.meetexApi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CommentController commentController;

    private CommentRequestDTO commentRequestDTO;
    private CommentResponseDTO commentResponseDTO;
    private CommentUpdateRequestDTO commentUpdateRequestDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        commentRequestDTO = new CommentRequestDTO();
        commentResponseDTO = new CommentResponseDTO();
        commentUpdateRequestDTO = new CommentUpdateRequestDTO();
        user = new User();
        user.setUserName("testuser");

        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("testuser").password("password").authorities("ROLE_USER").build();
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
    }

    @Test
    public void createCommentTest() {
        when(userService.getUserByUsername(anyString())).thenReturn(user);
        when(commentService.createComment(any(CommentRequestDTO.class), any(User.class))).thenReturn(commentResponseDTO);

        ResponseEntity<CommentResponseDTO> result = commentController.createComment(1L, commentRequestDTO);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(commentResponseDTO, result.getBody());
    }

    @Test
    public void getAllCommentsForPostTest() {
        when(commentService.getAllCommentsForPost(anyLong())).thenReturn(Collections.singletonList(commentResponseDTO));

        ResponseEntity<List<CommentResponseDTO>> result = commentController.getAllCommentsForPost(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(Collections.singletonList(commentResponseDTO), result.getBody());
    }

    @Test
    public void updateCommentTest() {
        when(commentService.updateComment(anyLong(), any(CommentUpdateRequestDTO.class))).thenReturn(commentResponseDTO);

        ResponseEntity<CommentResponseDTO> result = commentController.updateComment(1L, commentUpdateRequestDTO);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(commentResponseDTO, result.getBody());
    }

    @Test
    public void deleteCommentTest() {
        doNothing().when(commentService).delete(anyLong());

        ResponseEntity<?> result = commentController.deleteComment(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Comment deleted successfully", result.getBody());
    }
}
