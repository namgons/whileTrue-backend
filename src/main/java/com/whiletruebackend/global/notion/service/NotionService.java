package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.domain.Problem.vo.ProblemPage;
import com.whiletruebackend.global.notion.dto.response.*;

public interface NotionService {

    NotionTokenResponseDto requestNotionToken(String accessCode);

    CheckDatabaseResponseDto checkDatabase(String notionApiKey, String databaseId);

    ProblemPageListResponseDto getAllProblemList(String notionApiKey, String databaseId);

    SuccessResponseDto insertNewProblem(String notionApiKey, String databaseId, ProblemPage problemPage);

    CheckProblemResponseDto isProblemExists(String notionApiKey, String databaseId, ProblemPage problemPage);

}
