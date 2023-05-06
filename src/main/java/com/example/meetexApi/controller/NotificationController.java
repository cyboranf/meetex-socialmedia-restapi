package com.example.meetexApi.controller;

import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
