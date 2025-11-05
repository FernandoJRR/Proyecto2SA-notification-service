package com.sa.notification_service.notifications.infrastructure.output.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Representa una respuesta mínima con el correo del usuario.
 */
@Getter
@AllArgsConstructor
public class UserEmailResponseDTO {
    private String email;
}
