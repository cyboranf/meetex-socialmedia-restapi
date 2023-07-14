package com.example.meetexApi.controller;

import com.example.meetexApi.dto.comment.CommentRequestDTO;
import com.example.meetexApi.dto.comment.CommentResponseDTO;
import com.example.meetexApi.dto.comment.CommentUpdateRequestDTO;

import com.example.meetexApi.model.User;
import com.example.meetexApi.service.CommentService;
import com.example.meetexApi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    /**
     * @param postId
     * @param commentRequestDTO
     * @return DTO of new comment
     */
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long postId, @Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.getUserByUsername(authentication.getName());

        CommentResponseDTO newComment = commentService.createComment(commentRequestDTO, currentUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newComment);
    }

    /**
     * @param postId
     * @return List of comments DTO under post with id = {postId}
     */
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsForPost(@PathVariable Long postId) {
        List<CommentResponseDTO> comments = commentService.getAllCommentsForPost(postId);

        return ResponseEntity.ok(comments);
    }

    /**
     * @param commentId
     * @param commentUpdateRequest
     * @return DTO of updated comment
     */
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentUpdateRequestDTO commentUpdateRequest) {
        CommentResponseDTO updatedComment = commentService.updateComment(commentId, commentUpdateRequest);

        return ResponseEntity.ok(updatedComment);
    }

    /**
     * @param commentId
     * @return
     */
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
