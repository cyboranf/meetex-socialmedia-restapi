package com.example.meetexApi.controller;

import com.example.meetexApi.dto.notification.NotificationRequestDTO;
import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * @param userId
     * @return List of notification DTO to user with id = {userId}
     */
    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotifications(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getAllNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    /**
     * @param notificationId
     * @param notificationRequestDTO
     * @return DTO of updated Notification
     */
    @PutMapping("/notifications/{notificationId}")
    public ResponseEntity<NotificationResponseDTO> updateNotification(@PathVariable Long notificationId, @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        NotificationResponseDTO updatedNotification = notificationService.updateNotification(notificationId, notificationRequestDTO);
        return ResponseEntity.ok(updatedNotification);
    }

    /**
     * @param notificationId
     * @return
     */
    @DeleteMapping("/notifications/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}
