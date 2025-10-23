package com.sa.notification_service.notifications.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendNotificationOutputDTO {
    private boolean delivered;
    private String messageId;
    private String details;
}
