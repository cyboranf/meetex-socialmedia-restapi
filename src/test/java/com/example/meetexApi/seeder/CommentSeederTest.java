package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Comment;
import com.example.meetexApi.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentSeederTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentSeeder commentSeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateComment_whenCommentRepositoryIsEmpty() throws Exception {
        when(commentRepository.count()).thenReturn(0L);

        commentSeeder.run();

        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void run_shouldNotCreateComment_whenCommentRepositoryIsNotEmpty() throws Exception {
        when(commentRepository.count()).thenReturn(1L);

        commentSeeder.run();

        verify(commentRepository, times(0)).save(any(Comment.class));
    }
}
