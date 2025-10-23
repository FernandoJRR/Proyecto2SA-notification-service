package com.sa.notification_service.notifications.infrastructure.output.rest;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sa.notification_service.notifications.application.outputports.GetClientEmailOutputPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GetClientEmailAdapter implements GetClientEmailOutputPort {

    private final WebClient.Builder webClientBuilder;

    @Value("${app.userURL}")
    private String USER_SERVICE_URL;

    @Override
    public Optional<String> getRecipientEmail(UUID clientId) {
        final String requestUrl = USER_SERVICE_URL + "/api/v1/users/{clientId}";
        try {
            Optional<ClientResponse> client = webClientBuilder.build().get()
                .uri(requestUrl, clientId)
                .retrieve()
                .bodyToMono(ClientResponse.class)
                .blockOptional();

            return client.map(ClientResponse::email);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record ClientResponse(String email) { }
}
