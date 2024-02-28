package com.whiletruebackend.global.notion.dto.request;

import com.whiletruebackend.domain.Problem.vo.ProblemPage;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class NotionProblemPageRequestDto {

    private String notionApiKey;
    private String databaseId;
    private NotionProblemPage problemPage;

    public static NotionProblemPageRequestDto from(String notionApiKey, String databaseId, ProblemPage problemPage) {
        return new NotionProblemPageRequestDto(notionApiKey, databaseId, NotionProblemPage.builder()
                .siteType(problemPage.getSiteType().getName())
                .level(problemPage.getLevel())
                .number(problemPage.getNumber())
                .title(problemPage.getTitle())
                .url(problemPage.getUrl())
                .iconType(problemPage.getIconType().getName())
                .iconSrc(problemPage.getIconSrc()).build());
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    private static class NotionProblemPage {

        private String siteType;
        private String level;
        private String number;
        private String title;
        private String url;
        private String iconType;
        private String iconSrc;

    }

}
