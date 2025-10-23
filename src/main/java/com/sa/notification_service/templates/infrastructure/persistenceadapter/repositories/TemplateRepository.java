package com.sa.notification_service.templates.infrastructure.persistenceadapter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.notification_service.templates.infrastructure.persistenceadapter.models.TemplateEntity;

public interface TemplateRepository extends JpaRepository<TemplateEntity, String> {
    public Optional<TemplateEntity> findOneByCode(String code);
}
