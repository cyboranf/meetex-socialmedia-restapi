package com.example.meetexApi.service;

import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.model.Notification;
import com.example.meetexApi.repository.NotificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void delete(Notification notification) {
        notificationRepository.delete(notification);
    }

    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> findById(Long id) {
        return notificationRepository.findById(id);
    }

    public List<NotificationResponseDTO> getAllNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByToUser_Id(userId);
        return notifications.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private NotificationResponseDTO mapToDTO(Notification notification) {
        NotificationResponseDTO dto = new NotificationResponseDTO();
        dto.setId(notification.getId());
        dto.setDescription(notification.getDescription());
        dto.setFromUserId(notification.getFromUser().getId());
        dto.setToUserId(notification.getToUser().getId());
        dto.setDate(notification.getDate().toLocalDate());
        return dto;
    }
}
