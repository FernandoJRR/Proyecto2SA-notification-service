package com.sa.notification_service.notifications.infrastructure.output.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sa.notification_service.notifications.application.outputports.GetClientEmailOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetClientEmailAdapter implements GetClientEmailOutputPort {

    private final WebClient.Builder webClientBuilder;
    private static final String USER_SERVICE_URL = "http://gateway/api/v1/users";

    @Override
    public Optional<String> getRecipientEmail(UUID clientId) {
        final String requestUrl = USER_SERVICE_URL + "/" + clientId;
        try {
            Optional<ClientResponse> client = webClientBuilder.build().get()
                    .uri(requestUrl)
                    .retrieve()
                    .bodyToMono(ClientResponse.class)
                    .blockOptional();

            return client.map(ClientResponse::email);
        } catch (Exception ex) {
            return Optional.empty();
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private record ClientResponse(String email) {
    }
}
