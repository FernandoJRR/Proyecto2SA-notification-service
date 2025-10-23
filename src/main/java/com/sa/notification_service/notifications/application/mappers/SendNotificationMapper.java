package com.sa.notification_service.notifications.application.mappers;

import com.sa.notification_service.notifications.application.dtos.SendNotificationInputDTO;
import com.sa.notification_service.notifications.domain.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SendNotificationMapper {
    SendNotificationMapper INSTANCE = Mappers.getMapper(SendNotificationMapper.class);

    Notification toDomain(SendNotificationInputDTO inputDTO);
}
