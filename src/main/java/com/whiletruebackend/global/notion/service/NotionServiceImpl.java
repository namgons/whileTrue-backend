package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.domain.Problem.vo.ProblemPage;
import com.whiletruebackend.global.config.ConnectionConfig;
import com.whiletruebackend.global.notion.dto.request.NotionDatabaseRequestDto;
import com.whiletruebackend.global.notion.dto.request.NotionProblemPageRequestDto;
import com.whiletruebackend.global.notion.dto.request.NotionProblemRequestDto;
import com.whiletruebackend.global.notion.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NotionServiceImpl implements NotionService {

    private final ConnectionConfig config;
    private final RestTemplate restTemplate;

    @Override
    public NotionTokenResponseDto requestNotionToken(String accessCode) {
        String url = config.getNotionApiServerUrl() + "/notion/token/" + accessCode;
        return restTemplate.exchange(url, HttpMethod.GET, null, NotionTokenResponseDto.class).getBody();
    }

    @Override
    public CheckDatabaseResponseDto checkDatabase(String notionApiKey, String databaseId) {
        String url = config.getNotionApiServerUrl() + "/notion/database/check";
        HttpEntity<NotionDatabaseRequestDto> entity = new HttpEntity<>(new NotionDatabaseRequestDto(notionApiKey, databaseId));
        return restTemplate.exchange(url, HttpMethod.POST, entity, CheckDatabaseResponseDto.class).getBody();
    }

    @Override
    public ProblemPageListResponseDto getAllProblemList(String notionApiKey, String databaseId) {
        String url = config.getNotionApiServerUrl() + "/notion/problem/list";
        HttpEntity<NotionDatabaseRequestDto> entity = new HttpEntity<>(new NotionDatabaseRequestDto(notionApiKey, databaseId));
        return restTemplate.exchange(url, HttpMethod.POST, entity, ProblemPageListResponseDto.class).getBody();
    }

    @Override
    public SuccessResponseDto insertNewProblem(String notionApiKey, String databaseId, ProblemPage problemPage) {
        String url = config.getNotionApiServerUrl() + "/notion/problem/insert";
        HttpEntity<NotionProblemPageRequestDto> entity = new HttpEntity<>(NotionProblemPageRequestDto.from(notionApiKey, databaseId,
                                                                                                           problemPage));
        return restTemplate.exchange(url, HttpMethod.POST, entity, SuccessResponseDto.class).getBody();
    }

    @Override
    public CheckProblemResponseDto isProblemExists(String notionApiKey, String databaseId, ProblemPage problemPage) {
        String url = config.getNotionApiServerUrl() + "/notion/problem/check";
        HttpEntity<NotionProblemRequestDto> entity = new HttpEntity<>(NotionProblemRequestDto.from(notionApiKey, databaseId, problemPage));
        return restTemplate.exchange(url, HttpMethod.POST, entity, CheckProblemResponseDto.class).getBody();
    }
}
