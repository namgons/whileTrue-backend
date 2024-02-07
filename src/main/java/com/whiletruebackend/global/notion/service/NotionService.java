package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;

import java.util.List;

public interface NotionService {

    NotionAccessToken requestNotionToken(String accessCode);
    RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String notionDatabaseId);
    List<Problem> getProblemList(String notionApiKey, String notionDatabaseId);
}
