package com.example.meetexApi.controller;

import com.example.meetexApi.dto.post.PostRequestDTO;
import com.example.meetexApi.dto.post.PostResponseDTO;
import com.example.meetexApi.dto.post.PostUpdateRequest;
import com.example.meetexApi.model.User;
import com.example.meetexApi.service.PostService;
import com.example.meetexApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @param postRequestDTO
     * @param principal
     * @return DTO of new shared post
     */
    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody PostRequestDTO postRequestDTO, Principal principal) {
        User currentUser = userService.getUserByUsername(principal.getName());
        PostResponseDTO post = postService.sharePost(postRequestDTO, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    /**
     * @param postId
     * @return DTO of post with id = {postId}
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPostById(@PathVariable Long postId) {
        PostResponseDTO post = postService.getPostById(postId);
        return ResponseEntity.ok(post);
    }

    /**
     * @param postId
     * @param postUpdateRequest
     * @return DTO of updated Post
     */
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable Long postId, @Valid @RequestBody PostUpdateRequest postUpdateRequest) {
        PostResponseDTO updatedPost = postService.updatePost(postId, postUpdateRequest);

        return ResponseEntity.ok(updatedPost);
    }

    /**
     * @param postId
     * @return
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.delete(postId);
        return ResponseEntity.ok().build();
    }

    /**
     * @param postId
     * @return dto of Post with one like more
     */
    @PostMapping("/{postId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());
        PostResponseDTO updatedPost = postService.likePost(postId, currentUser.getId());
        return ResponseEntity.ok(updatedPost);
    }

    /**
     * @param postId
     * @return dto of Post with one like less
     */
    @DeleteMapping("/{postId}/unlike")
    public ResponseEntity<?> unlikePost(@PathVariable Long postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());
        PostResponseDTO updatedPost = postService.unlikePost(postId, currentUser.getId());
        return ResponseEntity.ok(updatedPost);
    }
}
