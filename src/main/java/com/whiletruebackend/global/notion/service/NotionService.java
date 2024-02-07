package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.global.notion.dto.RetrieveDatabaseResponseDto;

public interface NotionService {

    RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String notionDatabaseId);
}
