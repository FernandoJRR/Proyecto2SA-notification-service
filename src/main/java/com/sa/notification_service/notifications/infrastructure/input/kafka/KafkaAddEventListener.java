package com.sa.notification_service.notifications.infrastructure.input.kafka;

import com.sa.notification_service.notifications.application.dtos.SendNotificationInputDTO;
import com.sa.notification_service.notifications.application.inputports.SendNotificationInputPort;
import com.sap.common_lib.dto.response.notification.events.SendGenericMailEventDTO;
import com.sap.common_lib.events.groups.GroupsConstants;
import com.sap.common_lib.events.topics.TopicConstants;
import java.util.Map;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaAddEventListener {

    private final SendNotificationInputPort sendNotificationInputPort;

    @KafkaListener(
        topics = TopicConstants.REQUEST_GENERIC_MAIL,
        groupId = GroupsConstants.NOTIFICATION_SERVICE_GROUP_ID
    )
    public void onRequestGenericMail(@Payload SendGenericMailEventDTO message) {
        try {
            SendNotificationInputDTO inputDTO = SendNotificationInputDTO.builder()
                    .clientId(UUID.fromString(message.clientId()))
                    .templateVariables(Map.of("message", message.message()))
                    .build();

            sendNotificationInputPort.sendNotification(inputDTO);
            log.info(
                "Generic mail successfully processed for client {}",
                message.clientId());
        } catch (Exception exception) {
            log.error(
                "Failed to process generic mail payload: {}",
                message,
                exception);
            throw new IllegalStateException("Failed to process generic mail payload", exception);
        }
    }
}
