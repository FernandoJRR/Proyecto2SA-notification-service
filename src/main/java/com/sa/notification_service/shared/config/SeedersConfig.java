package com.sa.notification_service.shared.config;

import com.sa.notification_service.templates.infrastructure.persistenceadapter.models.TemplateEntity;
import com.sa.notification_service.templates.infrastructure.persistenceadapter.repositories.TemplateRepository;
import jakarta.transaction.Transactional;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev || prod || local")
@RequiredArgsConstructor
@Component
public class SeedersConfig implements CommandLineRunner {

    private static final String GENERIC_TEMPLATE_CODE = "generic-template";

    private final TemplateRepository templateRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void run(String... args) throws Exception {
        System.out.println("Init seeders...");
        if (templateRepository.count() > 0) {
            System.out.println("Seeders previously run.");
            return;
        }

        TemplateEntity genericTemplate = TemplateEntity.builder()
                .id(UUID.randomUUID())
                .code(GENERIC_TEMPLATE_CODE)
                .htmlContent("""
                        <!DOCTYPE html>
                        <html lang="en" xmlns:th="http://www.thymeleaf.org">
                        <head>
                            <meta charset="UTF-8">
                            <title>Generic Notification</title>
                        </head>
                        <body>
                            <p th:text="${message}">Generic message placeholder</p>
                        </body>
                        </html>
                        """)
                .variables(Map.of("message", "This is a sample message for the generic template."))
                .build();

        templateRepository.save(genericTemplate);

        System.out.println("Seeders finished.");
    }
}
