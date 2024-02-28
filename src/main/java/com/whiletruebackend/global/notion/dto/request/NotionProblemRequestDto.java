package com.whiletruebackend.global.notion.dto.request;

import com.whiletruebackend.domain.Problem.vo.Problem;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NotionProblemRequestDto {

    private String notionApiKey;
    private String databaseId;
    private NotionProblem problem;

    public static NotionProblemRequestDto from(String notionApiKey, String databaseId, Problem problem) {
        return new NotionProblemRequestDto(notionApiKey, databaseId, NotionProblem.builder()
                .siteType(problem.getSiteType().getName())
                .level(problem.getLevel())
                .number(problem.getNumber())
                .title(problem.getTitle())
                .url(problem.getUrl()).build());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    private static class NotionProblem {

        private String siteType;
        private String level;
        private String number;
        private String title;
        private String url;

    }

}
