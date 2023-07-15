package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Notification;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.NotificationRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(7)
@Profile("seed")
public class NotificationSeeder implements DatabaseSeeder {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationSeeder(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Notification:
     * "1": {
     * "description": "This is your first notification.",
     * "fromUser": 1,
     * "toUser": 2
     * }
     */
    @Override
    public void run(String... args) throws Exception {
        if (notificationRepository.count() == 0) {
            User fromUser = userRepository.findById(1L)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            User toUser = userRepository.findById(2L)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Notification notification = new Notification();
            notification.setDescription("This is your first notification.");
            notification.setFromUser(fromUser);
            notification.setToUser(toUser);
            notification.setDate(LocalDateTime.now());

            notificationRepository.save(notification);
        }
    }
}
