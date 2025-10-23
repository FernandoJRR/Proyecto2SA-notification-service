package com.sa.notification_service.templates.application.outputports;

import java.util.Map;

import com.sa.notification_service.templates.application.dtos.RenderTemplateDTO;
import com.sa.notification_service.templates.application.dtos.RenderTemplateOutputDTO;

public interface RenderTemplateOutputPort {
    RenderTemplateOutputDTO renderTemplate(String templateHtml, Map<String, Object> variables);
}
