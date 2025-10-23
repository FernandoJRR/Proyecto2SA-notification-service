package com.sa.notification_service.templates.application.outputports;

import java.util.Optional;

import com.sa.notification_service.templates.domain.Template;

public interface GetTemplateByCodeOutputPort {
    public Optional<Template> getByCode(String code);
}
