package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.response.*;

public interface NotionService {

    NotionTokenResponseDto requestNotionToken(String accessCode);

    CheckDatabaseResponseDto retrieveDatabase(String notionApiKey, String databaseId);

    ProblemPageListResponseDto getAllProblemList(String notionApiKey, String databaseId);

    CheckProblemResponseDto isProblemExists(String notionApiKey, String databaseId, Problem problem);

    SuccessResponseDto insertNewProblem(String notionApiKey, String databaseId, Problem problem);
}
