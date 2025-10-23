package com.sa.notification_service.notifications.application.usecases;

import com.sa.notification_service.notifications.application.dtos.SendNotificationDTO;
import com.sa.notification_service.notifications.application.dtos.SendNotificationInputDTO;
import com.sa.notification_service.notifications.application.dtos.SendNotificationOutputDTO;
import com.sa.notification_service.notifications.application.inputports.SendNotificationInputPort;
import com.sa.notification_service.notifications.application.mappers.SendNotificationMapper;
import com.sa.notification_service.notifications.application.outputports.SendNotificationOutputPort;
import com.sa.notification_service.notifications.domain.Notification;
import com.sa.notification_service.shared.config.properties.GenericMailProperties;
import com.sa.notification_service.templates.application.dtos.RenderTemplateInputDTO;
import com.sa.notification_service.templates.application.dtos.RenderTemplateOutputDTO;
import com.sa.notification_service.templates.application.inputports.RenderTemplateInputPort;
import com.sa.notification_service.notifications.application.outputports.GetClientEmailOutputPort;
import com.sap.common_lib.exception.InvalidArgumentException;
import com.sap.common_lib.exception.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@RequiredArgsConstructor
public class SendGenericNotificationUseCase implements SendNotificationInputPort {

    private final SendNotificationOutputPort sendNotificationOutputPort;
    private final RenderTemplateInputPort renderTemplateInputPort;
    private final GetClientEmailOutputPort getClientEmailOutputPort;
    private final GenericMailProperties genericMailProperties;

    @Override
    public SendNotificationOutputDTO sendNotification(SendNotificationInputDTO inputDTO) throws Exception {
        Notification notification = SendNotificationMapper.INSTANCE.toDomain(inputDTO);

        Objects.requireNonNull(notification.getClientId(), "El id del cliente es obligatorio");

        /*
        String recipientEmail = getClientEmailOutputPort
                .getRecipientEmail(notification.getClientId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
         */
        String recipientEmail = "kariasexual@tiffincrane.com";

        Map<String, Object> templateVariables = new HashMap<>();
        if (notification.getTemplateVariables() != null) {
            templateVariables.putAll(notification.getTemplateVariables());
        }

        if (!templateVariables.containsKey("message")
                || templateVariables.get("message") == null
                || templateVariables.get("message").toString().isBlank()) {
            throw new InvalidArgumentException("El mensaje es obligatorio.");
        }

        String subject = StringUtils.hasText(notification.getTitle())
                ? notification.getTitle()
                : genericMailProperties.getDefaultSubject();

        RenderTemplateInputDTO renderTemplateInputDTO =
                RenderTemplateInputDTO.builder()
                        .templateCode(genericMailProperties.getTemplateCode())
                        .variables(templateVariables)
                        .build();

        RenderTemplateOutputDTO renderedTemplate =
                renderTemplateInputPort.renderTemplate(renderTemplateInputDTO);

        SendNotificationDTO notificationDTO = SendNotificationDTO.builder()
                .title(subject)
                .fromEmail(notification.getFromEmail())
                .recipientEmail(recipientEmail)
                .htmlContent(renderedTemplate.getRenderedHtml())
                .build();
        return sendNotificationOutputPort.sendNotification(notificationDTO);
    }
}
