package com.example.excelHandler.service;

import com.example.excelHandler.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class ExternalApiService {
    private final WebClient webClient;
    private final String externalApiUrl;

    public ExternalApiService(
            WebClient webClient,
            @Value("${external.api.url}") String externalApiUrl) {
        this.webClient = webClient;
        this.externalApiUrl = externalApiUrl;
    }

    public ResponseDto callExternalApi(Object request) {

        // NON PROD CODE
        return ResponseDto.builder()
                .msgId("1428754e463456")
                .status("Success")
                .transactionRefNo("033PACC202068505").build();

        // PROD CODE
//        return webClient.post()
//                .uri(externalApiUrl)
//                .bodyValue(request)
//                .retrieve()
//                .onStatus(
//                        HttpStatusCode::is4xxClientError,
//                        response -> response.createException()
//                                .map(ex -> new ExternalApiException(
//                                        "External API returned client error: ["
//                                                + response.statusCode() + "], Message: " + ex.getMessage()
//                                ))
//                )
//                .onStatus(
//                        HttpStatusCode::is5xxServerError,
//                        response -> response.createException()
//                                .map(ex -> new ExternalApiException(
//                                        "External API returned client error: ["
//                                                + response.statusCode() + "], Message: " + ex.getMessage()
//                                ))
//                )
//                .bodyToMono(ResponseDto.class)
//                .onErrorMap( // Handles any network errors
//                        WebClientRequestException.class,
//                        ex -> new ExternalApiException(
//                                "Failed to communicate with external API"
//                        )
//                )
//                .block();
    }
}
