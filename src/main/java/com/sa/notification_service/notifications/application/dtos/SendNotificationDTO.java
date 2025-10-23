package com.sa.notification_service.notifications.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SendNotificationDTO {
    private String title;
    private String fromEmail;
    private String recipientEmail;
    private String htmlContent;
}
