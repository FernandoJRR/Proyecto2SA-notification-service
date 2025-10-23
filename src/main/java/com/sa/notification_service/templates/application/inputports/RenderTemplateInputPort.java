package com.sa.notification_service.templates.application.inputports;

import com.sa.notification_service.templates.application.dtos.RenderTemplateInputDTO;
import com.sa.notification_service.templates.application.dtos.RenderTemplateOutputDTO;
import com.sap.common_lib.exception.InvalidArgumentException;
import com.sap.common_lib.exception.NotFoundException;

public interface RenderTemplateInputPort {
    RenderTemplateOutputDTO renderTemplate(RenderTemplateInputDTO inputDTO) throws InvalidArgumentException, NotFoundException;
}
