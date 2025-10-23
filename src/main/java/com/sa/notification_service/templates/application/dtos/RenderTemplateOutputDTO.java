package com.sa.notification_service.templates.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RenderTemplateOutputDTO {
    private String renderedHtml;
}
