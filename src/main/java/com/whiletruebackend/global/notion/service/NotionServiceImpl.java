package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.request.CreatePageRequestDto;
import com.whiletruebackend.global.notion.dto.response.QueryDatabaseResponseDto;
import com.whiletruebackend.global.notion.dto.response.RequiredColumn;
import com.whiletruebackend.global.notion.dto.response.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;
import com.whiletruebackend.global.utils.ObjectMapperUtils;
import com.whiletruebackend.global.utils.WebClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

import static com.whiletruebackend.global.notion.dto.response.QueryDatabaseResponseDto.NotionPageProperty;

@Service
public class NotionServiceImpl implements NotionService {

    @Value("${oauth.client_id}")
    private String OAUTH_CLIENT_ID;

    @Value("${oauth.client_secret}")
    private String OAUTH_CLIENT_SECRET;

    @Value("${oauth.redirect_uri}")
    private String REDIRECT_URI;

    private final String NOTION_TOKEN_ENDPOINT = "https://api.notion.com/v1/oauth/token";
    private final String NOTION_DATABASE_ENDPOINT = "https://api.notion.com/v1/databases";
    private final String NOTION_PAGE_ENDPOINT = "https://api.notion.com/v1/pages";

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
    public RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String notionDatabaseId) {
        String url = NOTION_DATABASE_ENDPOINT + "/" + notionDatabaseId;
        WebClient notionClient = WebClientUtils.createNotionClient(url, notionApiKey);

        return notionClient.get()
                .retrieve()
                .bodyToMono(RetrieveDatabaseResponseDto.class)
                .block();
    }

    @Override
    public List<Problem> getProblemList(String notionApiKey, String notionDatabaseId) {
        String url = String.format("%s/%s/query", NOTION_DATABASE_ENDPOINT, notionDatabaseId);
        WebClient notionClient = WebClientUtils.createNotionClient(url, notionApiKey);

        Map<String, Object> formData = new HashMap<>();
        List<Map<String, String>> sortList = new ArrayList<>();
        sortList.add(Map.of(
                "property", RequiredColumn.RANDOM_NUMBER,
                "direction", "ascending"
        ));
        formData.put("sort", sortList);
        String json = ObjectMapperUtils.mapToString(formData);

        QueryDatabaseResponseDto response = notionClient.post()
                .bodyValue(json)
                .retrieve()
                .bodyToMono(QueryDatabaseResponseDto.class)
                .block();

        List<Problem> results = new ArrayList<>();
        response.getResults().forEach(notionPage -> {
            if (!notionPage.getArchived()) {
                NotionPageProperty property = notionPage.getProperties();
                results.add(Problem.builder()
                                    .problemSiteType(property.getProblemSite().getSelect().getName())
                                    .problemNumber(property.getProblemNumber().getNumber())
                                    .problemTitle(property.getProblemTitle().getTitle().get(0).getPlainText())
                                    .url(property.getProblemUrl().getUrl())
                                    .build());
            }
        });

        return results;
    }

    @Override
    public boolean isProblemExists(String notionApiKey, String databaseId, Problem problem) {
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

        QueryDatabaseResponseDto response = notionClient.post()
                .bodyValue(json)
                .retrieve()
                .bodyToMono(QueryDatabaseResponseDto.class)
                .block();

        return response.getResults().size() != 0;
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
