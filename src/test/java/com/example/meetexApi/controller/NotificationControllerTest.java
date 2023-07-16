package com.example.meetexApi.controller;

import com.example.meetexApi.dto.notification.NotificationRequestDTO;
import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        // Intentionally left blank
    }

    @Test
    public void getAllNotificationsTest() {
        NotificationResponseDTO response = new NotificationResponseDTO();
        List<NotificationResponseDTO> responseList = Collections.singletonList(response);

        when(notificationService.getAllNotifications(anyLong())).thenReturn(responseList);

        ResponseEntity<List<NotificationResponseDTO>> result = notificationController.getAllNotifications(1L);

        assertEquals(responseList, result.getBody());
    }

    @Test
    public void updateNotificationTest() {
        NotificationRequestDTO request = new NotificationRequestDTO();
        NotificationResponseDTO response = new NotificationResponseDTO();

        when(notificationService.updateNotification(anyLong(), any(NotificationRequestDTO.class))).thenReturn(response);

        ResponseEntity<NotificationResponseDTO> result = notificationController.updateNotification(1L, request);

        assertEquals(response, result.getBody());
    }

    @Test
    public void deleteNotificationTest() {
        ResponseEntity<?> result = notificationController.deleteNotification(1L);

        assertEquals(ResponseEntity.noContent().build(), result);
    }
}
