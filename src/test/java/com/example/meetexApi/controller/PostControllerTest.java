package com.example.meetexApi.controller;

import com.example.meetexApi.dto.post.PostRequestDTO;
import com.example.meetexApi.dto.post.PostResponseDTO;
import com.example.meetexApi.dto.post.PostUpdateRequest;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.PostService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    public void setUp() {
        // Intentionally left blank
    }

    @Test
    public void createPostTest() {
        PostRequestDTO request = new PostRequestDTO();
        PostResponseDTO response = new PostResponseDTO();
        User user = new User();

        when(userService.getUserByUsername(any(String.class))).thenReturn(user);
        when(postService.sharePost(eq(request), eq(user.getId()))).thenReturn(response);

        ResponseEntity<PostResponseDTO> result = postController.createPost(request, () -> "testUser");

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void getPostByIdTest() {
        PostResponseDTO response = new PostResponseDTO();

        when(postService.getPostById(any(Long.class))).thenReturn(response);

        ResponseEntity<PostResponseDTO> result = postController.getPostById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void updatePostTest() {
        PostUpdateRequest request = new PostUpdateRequest();
        PostResponseDTO response = new PostResponseDTO();

        when(postService.updatePost(any(Long.class), eq(request))).thenReturn(response);

        ResponseEntity<PostResponseDTO> result = postController.updatePost(1L, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void deletePostTest() {
        ResponseEntity<?> result = postController.deletePost(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void likePostTest() {
        PostResponseDTO response = new PostResponseDTO();
        User user = new User();

        when(userService.getUserByUsername(any(String.class))).thenReturn(user);
        when(postService.likePost(any(Long.class), eq(user.getId()))).thenReturn(response);

        Authentication authentication = new UsernamePasswordAuthenticationToken("testUser", "testPassword");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseEntity<?> result = postController.likePost(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void unlikePostTest() {
        PostResponseDTO response = new PostResponseDTO();
        User user = new User();

        when(userService.getUserByUsername(any(String.class))).thenReturn(user);
        when(postService.unlikePost(any(Long.class), eq(user.getId()))).thenReturn(response);

        Authentication authentication = new UsernamePasswordAuthenticationToken("testUser", "testPassword");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseEntity<?> result = postController.unlikePost(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
