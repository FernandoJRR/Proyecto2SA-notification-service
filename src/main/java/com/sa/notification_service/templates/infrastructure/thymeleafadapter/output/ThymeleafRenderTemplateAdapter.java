package com.sa.notification_service.templates.infrastructure.thymeleafadapter.output;

import com.sa.notification_service.templates.application.dtos.RenderTemplateDTO;
import com.sa.notification_service.templates.application.dtos.RenderTemplateOutputDTO;
import com.sa.notification_service.templates.application.outputports.RenderTemplateOutputPort;
import com.sa.notification_service.templates.domain.exceptions.TemplateRenderException;
import java.util.Collections;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

@Component
public class ThymeleafRenderTemplateAdapter implements RenderTemplateOutputPort {

    private final TemplateEngine inlineTemplateEngine;

    public ThymeleafRenderTemplateAdapter(SpringTemplateEngine templateEngine) {
        this.inlineTemplateEngine = createInlineTemplateEngine();
    }

    @Override
    public RenderTemplateOutputDTO renderTemplate(String templateHtml, Map<String, Object> variables) {
        try {
            Context context = new Context();
            context.setVariables(extractVariables(variables));

            System.out.println("MESSAGE");
            System.out.println(templateHtml);
            System.out.println("VAR");
            System.out.println(variables);

            String rendered = inlineTemplateEngine.process(templateHtml, context);
            return RenderTemplateOutputDTO.builder().renderedHtml(rendered).build();
        } catch (TemplateProcessingException exception) {
            throw new TemplateRenderException("Error al renderizar el template", exception);
        }
    }

    private Map<String, Object> extractVariables(Map<String, Object> variables) {
        return variables != null ? variables : Collections.emptyMap();
    }

    private TemplateEngine createInlineTemplateEngine() {
        TemplateEngine engine = new TemplateEngine();
        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        engine.setTemplateResolver(resolver);
        return engine;
    }
}
