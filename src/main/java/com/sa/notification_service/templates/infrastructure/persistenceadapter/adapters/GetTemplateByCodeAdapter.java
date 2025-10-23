package com.sa.notification_service.templates.infrastructure.persistenceadapter.adapters;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.notification_service.templates.application.outputports.GetTemplateByCodeOutputPort;
import com.sa.notification_service.templates.domain.Template;
import com.sa.notification_service.templates.infrastructure.persistenceadapter.mappers.TemplateRepositoryMapper;
import com.sa.notification_service.templates.infrastructure.persistenceadapter.models.TemplateEntity;
import com.sa.notification_service.templates.infrastructure.persistenceadapter.repositories.TemplateRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetTemplateByCodeAdapter implements GetTemplateByCodeOutputPort {
    private final TemplateRepository templateRepository;
    private final TemplateRepositoryMapper templateRepositoryMapper = TemplateRepositoryMapper.INSTANCE;

    @Override
    @Transactional(readOnly = true)
    public Optional<Template> getByCode(String code) {
        Optional<TemplateEntity> template = templateRepository.findOneByCode(code);
        return templateRepositoryMapper.toDomain(template);
    }
}
