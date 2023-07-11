package com.example.meetexApi.mapper;

import com.example.meetexApi.dto.notification.NotificationRequestDTO;
import com.example.meetexApi.dto.notification.NotificationResponseDTO;
import com.example.meetexApi.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "fromUser.id", target = "fromUser.id")
    @Mapping(source = "toUser.id", target = "toUser.id")
    NotificationResponseDTO notificationtoNotificationResponseDTO(Notification notification);

    @Mapping(source = "fromUserId", target = "fromUser.id")
    @Mapping(source = "toUserId", target = "toUser.id")
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "id", ignore = true)
    Notification notificationRequestDTOtoNotification(NotificationRequestDTO notificationRequestDTO);
}
