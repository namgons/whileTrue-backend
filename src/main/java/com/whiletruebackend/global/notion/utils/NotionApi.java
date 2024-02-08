package com.whiletruebackend.global.notion.utils;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.response.QueryDatabaseResponseDto;
import com.whiletruebackend.global.notion.dto.response.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;

public interface NotionApi {

    NotionAccessToken requestNotionToken(String accessCode);

    RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String databaseId);

    QueryDatabaseResponseDto getProblemList(String notionApiKey, String databaseId, String startCursor);

    QueryDatabaseResponseDto queryDatabaseBySiteTypeAndNumber(String notionApiKey, String databaseId, Problem problem);

    void insertNewProblem(String notionApiKey, String databaseId, Problem problem);
}
