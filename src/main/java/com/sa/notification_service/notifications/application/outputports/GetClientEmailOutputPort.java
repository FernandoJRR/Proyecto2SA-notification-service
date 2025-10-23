package com.sa.notification_service.notifications.application.outputports;

import java.util.Optional;
import java.util.UUID;

public interface GetClientEmailOutputPort {
    Optional<String> getRecipientEmail(UUID clientId);
}
