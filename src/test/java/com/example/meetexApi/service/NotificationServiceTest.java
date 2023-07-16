package com.example.meetexApi.service;

import com.example.meetexApi.dto.notification.NotificationRequestDTO;
import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.mapper.NotificationMapper;
import com.example.meetexApi.model.Notification;
import com.example.meetexApi.repository.NotificationRepository;
import com.example.meetexApi.validation.NotificationValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private NotificationMapper notificationMapper;

    @Mock
    private NotificationValidator notificationValidator;

    @InjectMocks
    private NotificationService notificationService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getAllNotificationsTest() {
        Long userId = 1L;
        Notification notification = new Notification();
        NotificationResponseDTO responseDTO = new NotificationResponseDTO();

        when(notificationRepository.findAllByToUser_Id(userId)).thenReturn(Arrays.asList(notification));
        when(notificationMapper.notificationtoNotificationResponseDTO(notification)).thenReturn(responseDTO);

        List<NotificationResponseDTO> result = notificationService.getAllNotifications(userId);

        assertEquals(1, result.size());
        assertEquals(responseDTO, result.get(0));
    }

    @Test
    public void updateNotificationTest() {
        Long notificationId = 1L;
        NotificationRequestDTO requestDTO = new NotificationRequestDTO();
        requestDTO.setDescription("Test Description");

        Notification updatedNotification = new Notification();
        updatedNotification.setDescription(requestDTO.getDescription());

        NotificationResponseDTO responseDTO = new NotificationResponseDTO();
        responseDTO.setDescription(requestDTO.getDescription());

        when(notificationMapper.notificationtoNotificationResponseDTO(updatedNotification)).thenReturn(responseDTO);

        NotificationResponseDTO result = notificationService.updateNotification(notificationId, requestDTO);

        assertEquals(responseDTO, result);
    }

    @Test
    public void deleteNotificationTest() {
        Long notificationId = 1L;
        Notification notification = new Notification();

        when(notificationValidator.deleteNotificationValidation(notificationId)).thenReturn(notification);
        doNothing().when(notificationRepository).delete(notification);

        notificationService.deleteNotification(notificationId);

        verify(notificationRepository, times(1)).delete(notification);
    }
}