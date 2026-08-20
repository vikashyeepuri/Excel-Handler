package com.example.excelupload.service;

import com.example.excelupload.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;

@Slf4j
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

    public ResponseDto callExternalApi(Object request) {

        // NON PROD CODE
        return ResponseDto.builder()
                .msgId("1428754e463456")
                .status("Success")
                .transactionRefNo("033PACC202068505").build();

        // PROD CODE
//        try {
//            return restClient.post()
//                    .uri(externalApiUrl)
//                    .body(request)
//                    .retrieve()
//                    .onStatus(
//                            HttpStatusCode::is4xxClientError,
//                            (req, res) -> {
//                                log.error(
//                                        "External API client error: {} - {}",
//                                        res.getStatusCode(),
//                                        res.getStatusText()
//                                );
//                            }
//                    )
//                    .onStatus(
//                            HttpStatusCode::is5xxServerError,
//                            (req, res) -> {
//                                log.error(
//                                        "External API server error: {} - {}",
//                                        res.getStatusCode(),
//                                        res.getStatusText()
//                                );
//                            }
//                    )
//                    .body(ResponseDto.class);
//        } catch (RestClientResponseException e) {
//            // API returned an HTTP error response (4xx / 5xx)
//            throw new RuntimeException(
//                    "External API returned error: " + e.getStatusCode(),
//                    e
//            );
//
//        } catch (RestClientException e) {
//            // Connection timeout, DNS failure, connection refused, etc.
//            log.error("Failed to communicate with external API", e);
//
//            throw new RuntimeException(
//                    "Failed to communicate with external API",
//                    e
//            );
//        }
    }
}
