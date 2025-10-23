package com.sa.notification_service.notifications.domain;

import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
    private String title;
    private String fromEmail;
    private String recipientEmail;
    private String templateCode;
    private Map<String, Object> templateVariables;
    private UUID clientId;
}
