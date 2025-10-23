package com.sa.notification_service.notifications.infrastructure.output.mail;

import com.sa.notification_service.notifications.application.dtos.SendNotificationDTO;
import com.sa.notification_service.notifications.application.dtos.SendNotificationOutputDTO;
import com.sa.notification_service.notifications.application.outputports.SendNotificationOutputPort;
import com.sa.notification_service.notifications.domain.exceptions.NotificationSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendNotificationMailSenderAdapter implements SendNotificationOutputPort {

    private final JavaMailSender mailSender;

    @Value("${app.notifications.default-from:no-reply@localhost}")
    private String defaultFrom;

    @Override
    public SendNotificationOutputDTO sendNotification(SendNotificationDTO notificationDTO) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            helper.setTo(notificationDTO.getRecipientEmail());
            helper.setSubject(notificationDTO.getTitle());
            helper.setText(notificationDTO.getHtmlContent(), true);
            helper.setFrom(defaultFrom);
            mailSender.send(mimeMessage);

            return SendNotificationOutputDTO.builder()
                    .delivered(true)
                    .messageId(UUID.randomUUID().toString())
                    .details("Notificacion enviada exitosamente")
                    .build();
        } catch (MailException | MessagingException exception) {
            throw new NotificationSendException("No se pudo enviar la notificacion", exception);
        }
    }
}
