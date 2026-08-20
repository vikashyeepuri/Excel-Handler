package com.example.excelupload.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ExternalApiService {
    private final RestClient restClient;
    private final String externalApiUrl;

    public ExternalApiService(
            RestClient.Builder restClientBuilder,
            @Value("${external.api.url}") String externalApiUrl) {
        this.restClient = restClientBuilder.build();
        this.externalApiUrl = externalApiUrl;
    }

    public String callExternalApi(Object request) {

        return restClient.post()
                .uri(externalApiUrl)
                .body(request)
                .retrieve()
                .body(String.class);
    }
}
