package com.sa.notification_service.templates.application.usecases;

import com.sa.notification_service.templates.application.dtos.RenderTemplateDTO;
import com.sa.notification_service.templates.application.dtos.RenderTemplateInputDTO;
import com.sa.notification_service.templates.application.dtos.RenderTemplateOutputDTO;
import com.sa.notification_service.templates.application.inputports.RenderTemplateInputPort;
import com.sa.notification_service.templates.application.outputports.GetTemplateByCodeOutputPort;
import com.sa.notification_service.templates.application.outputports.RenderTemplateOutputPort;
import com.sa.notification_service.templates.domain.Template;
import com.sap.common_lib.exception.InvalidArgumentException;
import com.sap.common_lib.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RenderTemplateUseCase implements RenderTemplateInputPort {

    private final RenderTemplateOutputPort renderTemplateOutputPort;
    private final GetTemplateByCodeOutputPort getTemplateByCodeOutputPort;

    @Override
    public RenderTemplateOutputDTO renderTemplate(RenderTemplateInputDTO inputDTO) throws InvalidArgumentException, NotFoundException {
        if (!hasText(inputDTO.getTemplateCode())) {
            throw new InvalidArgumentException("Es necesario enviar el codigo del Template");
        }

        Template foundTemplate = getTemplateByCodeOutputPort.getByCode(inputDTO.getTemplateCode())
            .orElseThrow(() -> new NotFoundException("Template no encontrado"));

        return renderTemplateOutputPort.renderTemplate(foundTemplate.getHtmlContent(), inputDTO.getVariables());
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
