package com.example.meetexApi.controller;

import com.example.meetexApi.dto.comment.CommentRequestDTO;
import com.example.meetexApi.dto.comment.CommentResponseDTO;
import com.example.meetexApi.dto.comment.CommentUpdateRequestDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.model.Comment;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable Long postId, @Valid @RequestBody CommentRequestDTO commentRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userService.findUserByUserName(authentication.getName());

        Comment newComment = commentService.createComment(postId, commentRequestDTO, currentUser);
        CommentResponseDTO commentResponseDTO = convertToCommentResponseDTO(newComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDTO>> getAllCommentsForPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getAllCommentsForPost(postId);
        List<CommentResponseDTO> commentResponseDTOs = comments.stream()
                .map(this::convertToCommentResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentResponseDTOs);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateComment(@PathVariable Long commentId, @Valid @RequestBody CommentUpdateRequestDTO commentUpdateRequest) {
        Comment updatedComment = commentService.updateComment(commentId, commentUpdateRequest);
        CommentResponseDTO commentResponseDTO = convertToCommentResponseDTO(updatedComment);
        return ResponseEntity.ok(commentResponseDTO);
    }

    private CommentResponseDTO convertToCommentResponseDTO(Comment comment) {
        CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
        commentResponseDTO.setId(comment.getId());
        commentResponseDTO.setText(comment.getText());
        commentResponseDTO.setSendDate(comment.getSendDate());
        commentResponseDTO.setSenderId(comment.getSender().getId());
        commentResponseDTO.setPostId(comment.getPost().getId());
        return commentResponseDTO;
    }

    private UserResponseDTO convertToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setUserName(user.getUserName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setLastName(user.getLastName());

        return userResponseDTO;
    }
}
