package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Message;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.MessageRepository;
import com.example.meetexApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MessageSeederTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MessageSeeder messageSeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateMessage_whenMessageRepositoryIsEmpty() throws Exception {
        when(messageRepository.count()).thenReturn(0L);

        User sender = new User();
        User recipient = new User();

        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(recipient));

        messageSeeder.run();

        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    public void run_shouldNotCreateMessage_whenMessageRepositoryIsNotEmpty() throws Exception {
        when(messageRepository.count()).thenReturn(1L);

        messageSeeder.run();

        verify(messageRepository, times(0)).save(any(Message.class));
    }
}
