package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Post;
import com.example.meetexApi.repository.PostRepository;
import com.example.meetexApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PostSeederTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostSeeder postSeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreatePost_whenPostRepositoryIsEmpty() throws Exception {
        when(postRepository.count()).thenReturn(0L);

        postSeeder.run();

        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    public void run_shouldNotCreatePost_whenPostRepositoryIsNotEmpty() throws Exception {
        when(postRepository.count()).thenReturn(1L);

        postSeeder.run();

        verify(postRepository, times(0)).save(any(Post.class));
    }
}
