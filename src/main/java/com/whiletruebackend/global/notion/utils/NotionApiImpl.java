package com.whiletruebackend.global.notion.utils;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.error.RestTemplateResponseErrorHandler;
import com.whiletruebackend.global.notion.dto.RequiredColumn;
import com.whiletruebackend.global.notion.dto.request.CreatePageRequestDto;
import com.whiletruebackend.global.notion.dto.response.QueryDatabaseResponseDto;
import com.whiletruebackend.global.notion.dto.response.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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

    private static RestTemplate restTemplate = new RestTemplate();

    static {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    }

    @Override
    public NotionAccessToken requestNotionToken(String accessCode) {
        String encoded = Base64.getEncoder().encodeToString(String.format("%s:%s", OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET).getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(encoded);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", "authorization_code");
        formData.put("code", accessCode);
        formData.put("redirect_uri", REDIRECT_URI);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(formData, headers);

        return restTemplate.exchange(
                NOTION_TOKEN_ENDPOINT,
                HttpMethod.POST,
                entity,
                NotionAccessToken.class).getBody();
    }

    @Override
    public RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String databaseId) {
        String url = String.format("%s/%s", NOTION_DATABASE_ENDPOINT, databaseId);

        HttpHeaders headers = createDefaultHeader(notionApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                RetrieveDatabaseResponseDto.class).getBody();
    }

    @Override
    public QueryDatabaseResponseDto getProblemList(String notionApiKey, String databaseId, String startCursor) {
        String url = String.format("%s/%s/query", NOTION_DATABASE_ENDPOINT, databaseId);

        HttpHeaders headers = createDefaultHeader(notionApiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        if (startCursor != null) {
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("start_cursor", startCursor);
            entity = new HttpEntity<>(formData.toString(), headers);
        }

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                QueryDatabaseResponseDto.class).getBody();
    }

    @Override
    public QueryDatabaseResponseDto queryDatabaseBySiteTypeAndNumber(String notionApiKey, String databaseId, Problem problem) {
        String url = String.format("%s/%s/query", NOTION_DATABASE_ENDPOINT, databaseId);

        HttpHeaders headers = createDefaultHeader(notionApiKey);

        Map<String, Object> formData = new HashMap<>();
        List<Map<String, Object>> filterList = new ArrayList<>();
        filterList.add(Map.of(
                "property", RequiredColumn.Name.PROBLEM_SITE,
                RequiredColumn.Type.PROBLEM_SITE, Map.of("equals", problem.getSiteType())
        ));
        filterList.add(Map.of(
                "property", RequiredColumn.Name.PROBLEM_NUMBER,
                RequiredColumn.Type.PROBLEM_NUMBER, Map.of("equals", Integer.parseInt(problem.getNumber()))
        ));
        formData.put("filter", Map.of("and", filterList));

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(formData, headers);

        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                QueryDatabaseResponseDto.class).getBody();
    }

    @Override
    public void insertNewProblem(String notionApiKey, String databaseId, Problem problem) {
        HttpHeaders headers = createDefaultHeader(notionApiKey);
        CreatePageRequestDto body = CreatePageRequestDto.from(databaseId, problem);
        HttpEntity<CreatePageRequestDto> entity = new HttpEntity<>(body, headers);

        restTemplate.exchange(
                NOTION_PAGE_ENDPOINT,
                HttpMethod.POST,
                entity,
                String.class);
    }

    private HttpHeaders createDefaultHeader(String notionApiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(notionApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Notion-Version", "2022-06-28");
        return headers;
    }
}
