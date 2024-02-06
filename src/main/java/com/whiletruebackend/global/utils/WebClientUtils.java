package com.whiletruebackend.global.utils;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class WebClientUtils {

    public static WebClient createTokenRequestWebClient(String url, String basicAuth) {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.setBasicAuth(basicAuth);
                }).build();
    }

    public static WebClient createNotionClient(String url, String notionApiKey) {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.setBearerAuth(notionApiKey);
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.set("Notion-Version", "2022-06-28");
                }).build();
    }
}
