package com.example.meetexApi.controller;

import com.example.meetexApi.dto.notification.NotificationRequestDTO;
import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotifications(@PathVariable Long userId) {
        List<NotificationResponseDTO> notifications = notificationService.getAllNotifications(userId);
        return ResponseEntity.ok(notifications);
    }

    @PutMapping("/notifications/{notificationId}")
    public ResponseEntity<NotificationResponseDTO> updateNotification(@PathVariable Long notificationId, @Valid @RequestBody NotificationRequestDTO notificationRequestDTO) {
        NotificationResponseDTO updatedNotification = notificationService.updateNotification(notificationId, notificationRequestDTO);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/notifications/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}
