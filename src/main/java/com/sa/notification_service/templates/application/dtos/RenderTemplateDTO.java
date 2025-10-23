package com.sa.notification_service.templates.application.dtos;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RenderTemplateDTO {
    private String code;
    private String htmlContent;
    private Map<String, Object> variables;
}
