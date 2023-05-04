package com.example.meetexApi.service;

import com.example.meetexApi.dto.post.PostRequestDTO;
import com.example.meetexApi.dto.post.PostUpdateRequest;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.PostRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post createPost(PostRequestDTO postRequestDTO, Long userId) {
        Post post = new Post();
        post.setTitle(postRequestDTO.getTitle());
        post.setText(postRequestDTO.getText());
        post.setSendDate(LocalDateTime.now());
        User user = new User();
        user.setId(userId);
        post.setSender(user);
        return postRepository.save(post);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Post not found with ID: " + postId));
    }
    public Post updatePost(Long postId, PostUpdateRequest postUpdateRequest) {
        Post post = getPostById(postId);
        post.setTitle(postUpdateRequest.getTitle());
        post.setText(postUpdateRequest.getText());
        return postRepository.save(post);
    }

    public void deletePost(Long postId) {
        Post post = getPostById(postId);
        postRepository.delete(post);
    }



}
