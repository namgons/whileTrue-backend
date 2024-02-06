package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.global.notion.dto.NotionDatabase;

public interface NotionService {

    NotionDatabase retrieveDatabase(String notionApiKey, String notionDatabaseId);
}
