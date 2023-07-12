package com.example.meetexApi.service;

import com.example.meetexApi.dto.notification.NotificationRequestDTO;
import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.mapper.NotificationMapper;
import com.example.meetexApi.model.Notification;
import com.example.meetexApi.repository.NotificationRepository;
import com.example.meetexApi.validation.NotificationValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    private final NotificationValidator notificationValidator;

    public NotificationService(NotificationRepository notificationRepository, NotificationMapper notificationMapper, NotificationValidator notificationValidator) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.notificationValidator = notificationValidator;
    }

    /**
     * @param userId
     * @return dto's of all users notification
     */
    public List<NotificationResponseDTO> getAllNotifications(Long userId) {
        notificationValidator.getAllNotificationValidation(userId);
        List<Notification> notifications = notificationRepository.findAllByToUser_Id(userId);

        return notifications.stream().map(notificationMapper::notificationtoNotificationResponseDTO).collect(Collectors.toList());
    }

    /**
     * @param notificationId
     * @param notificationRequestDTO
     * @return DTO of updated notification
     */
    public NotificationResponseDTO updateNotification(Long notificationId, NotificationRequestDTO notificationRequestDTO) {
        notificationValidator.updateNotificationValidation(notificationId, notificationRequestDTO);

        Notification updatingNotification = new Notification();

        updatingNotification.setDescription(notificationRequestDTO.getDescription());

        Notification savedNotification = notificationRepository.save(updatingNotification);

        return notificationMapper.notificationtoNotificationResponseDTO(savedNotification);
    }

    /**
     * @param notificationId
     */
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationValidator.deleteNotificationValidation(notificationId);

        notificationRepository.delete(notification);
    }
}
