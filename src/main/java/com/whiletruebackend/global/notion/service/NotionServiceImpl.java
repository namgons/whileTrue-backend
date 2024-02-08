package com.whiletruebackend.global.notion.service;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.response.QueryDatabaseResponseDto;
import com.whiletruebackend.global.notion.dto.response.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.utils.NotionApi;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotionServiceImpl implements NotionService {

    private final NotionApi notionApi;

    public NotionAccessToken requestNotionToken(String accessCode) {
        return notionApi.requestNotionToken(accessCode);
    }

    @Override
    public RetrieveDatabaseResponseDto retrieveDatabase(String notionApiKey, String databaseId) {
        return notionApi.retrieveDatabase(notionApiKey, databaseId);
    }

    @Override
    public List<Problem> getAllProblemList(String notionApiKey, String databaseId) {
        List<Problem> results = new ArrayList<>();
        QueryDatabaseResponseDto response;
        String startCursor = null;

        while (true) {
            response = notionApi.getProblemList(notionApiKey, databaseId, startCursor);
            response.getResults().forEach(notionPage -> {
                if (!notionPage.getArchived()) {
                    results.add(Problem.builder()
                                        .problemSiteType(notionPage.getProblemSite())
                                        .problemNumber(notionPage.getProblemNumber())
                                        .problemTitle(notionPage.getProblemTitle())
                                        .url(notionPage.getProblemUrl())
                                        .build());
                }
            });

            if (response.getHasMore()) {
                startCursor = response.getNextCursor();
            } else {
                break;
            }
        }

        return results;
    }

    @Override
    public boolean isProblemExists(String notionApiKey, String databaseId, Problem problem) {
        QueryDatabaseResponseDto response = notionApi.queryDatabaseBySiteTypeAndNumber(notionApiKey, databaseId, problem);
        return response.getResults().size() != 0;
    }

    @Override
    public void insertNewProblem(String notionApiKey, String databaseId, Problem problem) {
        notionApi.insertNewProblem(notionApiKey, databaseId, problem);
    }
}
