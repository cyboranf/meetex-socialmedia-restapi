package com.example.meetexApi.service;

import com.example.meetexApi.dto.post.PostRequestDTO;
import com.example.meetexApi.dto.post.PostResponseDTO;
import com.example.meetexApi.dto.post.PostUpdateRequest;
import com.example.meetexApi.mapper.PostMapper;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.PostRepository;
import com.example.meetexApi.repository.UserRepository;
import com.example.meetexApi.validation.PostValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Optional;

import static org.mockito.Mockito.*;

class PostServiceTest {

    private PostService postService;

    @Mock
    private PostRepository postRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PostValidator postValidator;
    @Mock
    private PostMapper postMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postService = new PostService(postRepository, userRepository, postValidator, postMapper);
    }

    @Test
    void delete() {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);
        when(postValidator.deleteValidation(postId)).thenReturn(post);

        postService.delete(postId);

        verify(postRepository).delete(post);
    }

    @Test
    void sharePost() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        PostRequestDTO postRequestDTO = new PostRequestDTO();
        postRequestDTO.setTitle("Test title");
        postRequestDTO.setText("Test text");

        Post post = new Post();
        post.setSender(user);
        post.setTitle(postRequestDTO.getTitle());
        post.setText(postRequestDTO.getText());

        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setTitle(post.getTitle());
        postResponseDTO.setText(post.getText());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(postMapper.postToPostResponseDTO(any(Post.class))).thenReturn(postResponseDTO);

        PostResponseDTO sharedPost = postService.sharePost(postRequestDTO, userId);

        verify(postRepository).save(any(Post.class));
    }

    @Test
    void getPostById() {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostResponseDTO postResponse = postService.getPostById(postId);

        verify(postRepository).findById(postId);
    }

    @Test
    void updatePost() {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);

        PostUpdateRequest postUpdateRequest = new PostUpdateRequest();
        postUpdateRequest.setTitle("Updated title");
        postUpdateRequest.setText("Updated text");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostResponseDTO updatedPost = postService.updatePost(postId, postUpdateRequest);

        verify(postRepository).save(any(Post.class));
    }

    @Test
    void likePost() {
        Long postId = 1L;
        Long userId = 1L;

        Post post = new Post();
        post.setId(postId);

        User user = new User();
        user.setId(userId);

        when(postValidator.likingPostValidation(postId, userId)).thenReturn(user);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostResponseDTO likedPost = postService.likePost(postId, userId);

        verify(postRepository).save(any(Post.class));
    }

    @Test
    void unlikePost() {
        Long postId = 1L;
        Long userId = 1L;

        Post post = new Post();
        post.setId(postId);

        User user = new User();
        user.setId(userId);

        when(postValidator.unlikingPostValidation(postId, userId)).thenReturn(user);
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostResponseDTO unlikedPost = postService.unlikePost(postId, userId);

        verify(postRepository).save(any(Post.class));
    }
}
