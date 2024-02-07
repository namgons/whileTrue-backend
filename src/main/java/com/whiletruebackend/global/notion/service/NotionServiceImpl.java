package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.global.notion.dto.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.utils.WebClientUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotionServiceImpl implements NotionService {

    private final String NOTION_DATABASE_ENDPOINT = "https://api.notion.com/v1/databases";

    @Override
    public RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String notionDatabaseId) {
        String url = NOTION_DATABASE_ENDPOINT + "/" + notionDatabaseId;
        WebClient notionClient = WebClientUtils.createNotionClient(url, notionApiKey);

        RetrieveDatabaseResponseDto retrieveDatabaseResponseDto = notionClient.get()
                .retrieve()
                .bodyToMono(RetrieveDatabaseResponseDto.class)
                .block();

        return retrieveDatabaseResponseDto;
    }
}
