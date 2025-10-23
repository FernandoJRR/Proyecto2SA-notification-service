package com.sa.notification_service;

import com.sa.notification_service.shared.config.properties.GenericMailProperties;
import com.sap.common_lib.security.web.SecurityConfig;
import com.sap.common_lib.security.web.WebClientConfig;
import com.sap.common_lib.security.web.filter.MicroServiceFilter;
import com.sap.common_lib.util.DateUtils;
import com.sap.common_lib.util.PublicEndpointUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfigurationProperties(GenericMailProperties.class)
@Import({ PublicEndpointUtil.class, SecurityConfig.class, MicroServiceFilter.class, DateUtils.class, WebClientConfig.class })
public class NotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }
}
