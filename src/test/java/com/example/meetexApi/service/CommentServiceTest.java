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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentValidator commentValidator;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        // Intentionally left blank
    }

    @Test
    public void createCommentTest() {
        CommentRequestDTO requestDTO = new CommentRequestDTO();
        Comment comment = new Comment();
        CommentResponseDTO responseDTO = new CommentResponseDTO();
        User user = new User();
        Post post = new Post();

        when(commentValidator.createCommentValidation(any(CommentRequestDTO.class))).thenReturn(post);
        when(commentMapper.commentRequestDTOtoComment(requestDTO)).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(commentMapper.commentToCommentResponseDTO(comment)).thenReturn(responseDTO);

        CommentResponseDTO result = commentService.createComment(requestDTO, user);

        assertEquals(responseDTO, result);
    }

    @Test
    public void getAllCommentsForPostTest() {
        Long postId = 1L;
        Post post = new Post();
        Comment comment = new Comment();
        List<Comment> comments = Arrays.asList(comment);
        CommentResponseDTO responseDTO = new CommentResponseDTO();
        List<CommentResponseDTO> responseDTOs = Arrays.asList(responseDTO);

        when(commentValidator.getAllCommentsForPostValidation(any(Long.class))).thenReturn(post);
        when(commentRepository.findByPost(post)).thenReturn(comments);
        when(commentMapper.commentToCommentResponseDTO(comment)).thenReturn(responseDTO);

        List<CommentResponseDTO> result = commentService.getAllCommentsForPost(postId);

        assertEquals(responseDTOs, result);
    }

    @Test
    public void updateCommentTest() {
        Long id = 1L;
        Comment comment = new Comment();
        CommentUpdateRequestDTO requestDTO = new CommentUpdateRequestDTO();
        CommentResponseDTO responseDTO = new CommentResponseDTO();

        when(commentValidator.updateCommentValidation(any(Long.class))).thenReturn(comment);
        when(commentRepository.save(comment)).thenReturn(comment);
        when(commentMapper.commentToCommentResponseDTO(comment)).thenReturn(responseDTO);

        CommentResponseDTO result = commentService.updateComment(id, requestDTO);

        assertEquals(responseDTO, result);
    }

}
