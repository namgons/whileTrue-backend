package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.global.notion.dto.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;

public interface NotionService {

    NotionAccessToken requestNotionToken(String accessCode);
    RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String notionDatabaseId);
}
