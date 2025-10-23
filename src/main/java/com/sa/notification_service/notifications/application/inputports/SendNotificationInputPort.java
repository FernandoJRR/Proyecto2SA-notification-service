package com.sa.notification_service.notifications.application.inputports;

import com.sa.notification_service.notifications.application.dtos.SendNotificationInputDTO;
import com.sa.notification_service.notifications.application.dtos.SendNotificationOutputDTO;

public interface SendNotificationInputPort {
    SendNotificationOutputDTO sendNotification(SendNotificationInputDTO inputDTO) throws Exception;
}
