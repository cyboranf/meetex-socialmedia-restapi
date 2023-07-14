package com.example.meetexApi.service;

import com.example.meetexApi.dto.comment.CommentRequestDTO;
import com.example.meetexApi.dto.comment.CommentResponseDTO;
import com.example.meetexApi.dto.comment.CommentUpdateRequestDTO;
import com.example.meetexApi.mapper.CommentMapper;
import com.example.meetexApi.model.Comment;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CommentRepository;
import com.example.meetexApi.repository.PostRepository;
import com.example.meetexApi.validation.CommentValidator;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentValidator commentValidator;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, CommentValidator commentValidator, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentValidator = commentValidator;
        this.commentMapper = commentMapper;
    }

    /**
     * @param id
     */
    public void delete(Long id) {
        commentRepository.delete(commentValidator.deleteValidation(id));
    }

    /**
     * @param commentRequestDTO
     * @param user
     * @return CommentResponseDTO of new comment
     */
    public CommentResponseDTO createComment(CommentRequestDTO commentRequestDTO, User user) {
        Post post = commentValidator.createCommentValidation(commentRequestDTO);

        Comment comment = commentMapper.commentRequestDTOtoComment(commentRequestDTO);

        comment.setSender(user);
        comment.setPost(post);
        comment.setSendDate(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);
        post.setCommentCount(post.getCommentCount() + 1);

        postRepository.save(post);

        return commentMapper.commentToCommentResponseDTO(savedComment);
    }

    /**
     * @param postId
     * @return list of CommentResponseDTO of post with id = postId
     */
    public List<CommentResponseDTO> getAllCommentsForPost(Long postId) {
        Post post = commentValidator.getAllCommentsForPostValidation(postId);

        List<Comment> commentsOfPost = commentRepository.findByPost(post);

        return commentsOfPost.stream()
                .map(commentMapper::commentToCommentResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param commentId
     * @param commentUpdateRequest
     * @return DTO of updated comment
     */
    public CommentResponseDTO updateComment(Long commentId, CommentUpdateRequestDTO commentUpdateRequest) {
        Comment comment = commentValidator.updateCommentValidation(commentId);

        comment.setText(commentUpdateRequest.getText());

        Comment savedComment = commentRepository.save(comment);

        return commentMapper.commentToCommentResponseDTO(savedComment);
    }
}