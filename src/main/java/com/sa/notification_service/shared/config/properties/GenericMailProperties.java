package com.sa.notification_service.shared.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.notifications.generic-mail")
public class GenericMailProperties {

    /**
     * Template code used to render the generic notification email.
     */
    private String templateCode = "generic-template";

    /**
     * Default subject used when the event does not provide one.
     */
    private String defaultSubject = "Generic Notification";
}
