package com.example.meetexApi.service;

import com.example.meetexApi.dto.comment.CommentRequestDTO;
import com.example.meetexApi.model.Comment;
import com.example.meetexApi.model.Post;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;

    public CommentService(PostService postService, CommentRepository commentRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(Long postId, CommentRequestDTO commentRequestDTO, User user) {
        Post post = postService.getPostById(postId);
        Comment comment = new Comment();
        comment.setText(commentRequestDTO.getText());
        comment.setSender(user);
        comment.setPost(post);
        comment.setSendDate(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        post.getComments().add(savedComment);
        post.setCommentCount(post.getCommentCount() + 1);
        postService.save(post);

        return savedComment;
    }

}