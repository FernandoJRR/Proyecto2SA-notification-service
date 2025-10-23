package com.sa.notification_service.templates.infrastructure.persistenceadapter.mappers;

import com.sa.notification_service.templates.domain.Template;
import com.sa.notification_service.templates.infrastructure.persistenceadapter.models.TemplateEntity;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemplateRepositoryMapper {
    TemplateRepositoryMapper INSTANCE = Mappers.getMapper(TemplateRepositoryMapper.class);

    @Mapping(target = "variables", expression = "java(convertVariables(entity.getVariables()))")
    Template toDomain(TemplateEntity entity);

    default Optional<Template> toDomain(Optional<TemplateEntity> entity) {
        return entity.map(this::toDomain);
    }

    default Map<String, Object> convertVariables(Map<String, String> variables) {
        if (variables == null) {
            return null;
        }
        Map<String, Object> converted = new HashMap<>();
        variables.forEach(converted::put);
        return converted;
    }
}
