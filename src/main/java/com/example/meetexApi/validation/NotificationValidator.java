package com.example.meetexApi.validation;

import com.example.meetexApi.dto.notification.NotificationRequestDTO;
import com.example.meetexApi.exception.notification.InvalidNotificationException;
import com.example.meetexApi.exception.notification.NotificationNotFoundException;
import com.example.meetexApi.exception.user.UserNotFoundException;
import com.example.meetexApi.model.Notification;
import com.example.meetexApi.repository.NotificationRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationValidator {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationValidator(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param userId
     */
    public void getAllNotificationValidation(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Can not found user with id = " + userId));
    }

    /**
     * @param notificationId
     * @param notificationRequestDTO
     */
    public void updateNotificationValidation(Long notificationId, NotificationRequestDTO notificationRequestDTO) {
        notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Can not found notification with id = " + notificationId));
        if (notificationRequestDTO.getDescription() == null || notificationRequestDTO.getDescription().length() > 1000) {
            throw new InvalidNotificationException("Description of notification can not be null and can not have more than 1000 characters.");
        }
    }

    /**
     * @param notificationId
     */
    public Notification deleteNotificationValidation(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Can not found notification with id = " + notificationId));
        return notification;
    }

}
