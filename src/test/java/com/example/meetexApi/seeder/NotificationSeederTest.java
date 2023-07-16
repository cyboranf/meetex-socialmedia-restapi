package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Notification;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.NotificationRepository;
import com.example.meetexApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotificationSeederTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private NotificationSeeder notificationSeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateNotification_whenNotificationRepositoryIsEmpty() throws Exception {
        when(notificationRepository.count()).thenReturn(0L);

        User fromUser = new User();
        User toUser = new User();

        when(userRepository.findById(1L)).thenReturn(Optional.of(fromUser));
        when(userRepository.findById(2L)).thenReturn(Optional.of(toUser));

        notificationSeeder.run();

        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    public void run_shouldNotCreateNotification_whenNotificationRepositoryIsNotEmpty() throws Exception {
        when(notificationRepository.count()).thenReturn(1L);

        notificationSeeder.run();

        verify(notificationRepository, times(0)).save(any(Notification.class));
    }
}
