package com.sa.notification_service.notifications.application.outputports;

import com.sa.notification_service.notifications.application.dtos.SendNotificationDTO;
import com.sa.notification_service.notifications.application.dtos.SendNotificationOutputDTO;

public interface SendNotificationOutputPort {
    SendNotificationOutputDTO sendNotification(SendNotificationDTO notificationDTO);
}
