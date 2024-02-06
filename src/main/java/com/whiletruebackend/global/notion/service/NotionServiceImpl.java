package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.global.notion.dto.NotionDatabase;
import com.whiletruebackend.global.utils.WebClientUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotionServiceImpl implements NotionService {

    private final String NOTION_DATABASE_ENDPOINT = "https://api.notion.com/v1/databases";

    @Override
    public void retrieveDatabase(String notionApiKey, String notionDatabaseId) {
        String url = NOTION_DATABASE_ENDPOINT + "/" + notionDatabaseId;
        WebClient notionClient = WebClientUtils.createNotionClient(notionDatabaseId, notionApiKey);

        NotionDatabase notionDatabase = notionClient.get()
                .retrieve()
                .bodyToMono(NotionDatabase.class)
                .block();
        

    }
}
