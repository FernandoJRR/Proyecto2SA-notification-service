package com.sa.notification_service.templates.infrastructure.persistenceadapter.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "template")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Lob
    @Column(name = "html_content", nullable = false)
    private String htmlContent;

    @ElementCollection
    @CollectionTable(name = "template_variables", joinColumns = @JoinColumn(name = "template_id"))
    @MapKeyColumn(name = "variable_key")
    @Column(name = "variable_value")
    private Map<String, String> variables;
}
