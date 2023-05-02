package com.example.meetexApi.controller;

import com.example.meetexApi.dto.post.PostRequestDTO;
import com.example.meetexApi.dto.post.PostResponseDTO;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.PostService;
import com.example.meetexApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO, Principal principal) {
        User currentUser = userService.findUserByUserName(principal.getName());
        Post newPost = postService.createPost(postRequestDTO, currentUser.getId());
        PostResponseDTO postResponseDTO = convertToPostResponseDTO(newPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponseDTO);
    }

    private PostResponseDTO convertToPostResponseDTO(Post post) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        postResponseDTO.setId(post.getId());
        postResponseDTO.setTitle(post.getTitle());
        postResponseDTO.setText(post.getText());
        postResponseDTO.setSendDate(post.getSendDate());
        postResponseDTO.setSenderId(post.getSender().getId());

        return postResponseDTO;
    }
}
