package com.sa.notification_service.templates.domain;

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
public class Template {
    private UUID id;
    private String code;
    private String htmlContent;
    private Map<String, Object> variables;
}
