package com.whiletruebackend.global.notion.utils;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.request.CreatePageRequestDto;
import com.whiletruebackend.global.notion.dto.response.QueryDatabaseResponseDto;
import com.whiletruebackend.global.notion.dto.response.RequiredColumn;
import com.whiletruebackend.global.notion.dto.response.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;
import com.whiletruebackend.global.utils.ObjectMapperUtils;
import com.whiletruebackend.global.utils.WebClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Component
public class NotionApiImpl implements NotionApi {

    @Value("${oauth.client_id}")
    private String OAUTH_CLIENT_ID;

    @Value("${oauth.client_secret}")
    private String OAUTH_CLIENT_SECRET;

    @Value("${oauth.redirect_uri}")
    private String REDIRECT_URI;

    private final String NOTION_TOKEN_ENDPOINT = "https://api.notion.com/v1/oauth/token";
    private final String NOTION_DATABASE_ENDPOINT = "https://api.notion.com/v1/databases";
    private final String NOTION_PAGE_ENDPOINT = "https://api.notion.com/v1/pages";

    @Override
    public NotionAccessToken requestNotionToken(String accessCode) {
        String encoded = Base64.getEncoder().encodeToString(String.format("%s:%s", OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET).getBytes());
        WebClient webClient = WebClientUtils.createTokenRequestWebClient(NOTION_TOKEN_ENDPOINT, encoded);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", accessCode);
        formData.add("redirect_uri", REDIRECT_URI);

        return webClient.post()
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(NotionAccessToken.class)
                .block();
    }

    @Override
    public RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String databaseId) {
        String url = String.format("%s/%s", NOTION_DATABASE_ENDPOINT, databaseId);
        WebClient notionClient = WebClientUtils.createNotionClient(url, notionApiKey);

        return notionClient.get()
                .retrieve()
                .bodyToMono(RetrieveDatabaseResponseDto.class)
                .block();
    }

    @Override
    public QueryDatabaseResponseDto getProblemList(String notionApiKey, String databaseId, String startCursor) {
        String url = String.format("%s/%s/query", NOTION_DATABASE_ENDPOINT, databaseId);
        WebClient notionClient = WebClientUtils.createNotionClient(url, notionApiKey);

        if (startCursor != null) {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("start_cursor", "startCursor");

            return notionClient.post()
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(QueryDatabaseResponseDto.class)
                    .block();
        }

        return notionClient.post()
                .retrieve()
                .bodyToMono(QueryDatabaseResponseDto.class)
                .block();
    }

    @Override
    public QueryDatabaseResponseDto queryDatabaseBySiteTypeAndNumber(String notionApiKey, String databaseId, Problem problem) {
        String url = String.format("%s/%s/query", NOTION_DATABASE_ENDPOINT, databaseId);
        WebClient notionClient = WebClientUtils.createNotionClient(url, notionApiKey);

        Map<String, Object> formData = new HashMap<>();
        List<Map<String, Object>> filterList = new ArrayList<>();
        filterList.add(Map.of(
                "property", RequiredColumn.PROBLEM_SITE,
                "select", Map.of("equals", problem.getProblemSiteType())
        ));
        filterList.add(Map.of(
                "property", RequiredColumn.PROBLEM_NUMBER,
                "number", Map.of("equals", problem.getProblemNumber())
        ));
        formData.put("and", filterList);
        String json = ObjectMapperUtils.mapToString(formData);

        return notionClient.post()
                .bodyValue(json)
                .retrieve()
                .bodyToMono(QueryDatabaseResponseDto.class)
                .block();
    }

    @Override
    public void insertNewProblem(String notionApiKey, String databaseId, Problem problem) {
        WebClient notionClient = WebClientUtils.createNotionClient(NOTION_PAGE_ENDPOINT, notionApiKey);

        CreatePageRequestDto body = CreatePageRequestDto.from(databaseId, problem);
        notionClient.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Object.class)
                .block();
    }
}
