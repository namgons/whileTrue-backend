package com.whiletruebackend.global.notion.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.response.RequiredColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreatePageRequestDto {

    private Parent parent;
    private Properties properties;

    public static CreatePageRequestDto from(String databaseId, Problem problem) {

        Properties properties = Properties.builder()
                .problemSite(NotionRequestProperty.Select.create(problem.getProblemSiteType()))
                .problemNumber(new NotionRequestProperty.Number(Integer.parseInt(problem.getProblemNumber())))
                .problemTitle(NotionRequestProperty.Title.create(problem.getProblemTitle()))
                .problemUrl(new NotionRequestProperty.Url(problem.getUrl()))
                .build();

        return CreatePageRequestDto.builder()
                .parent(new Parent(databaseId))
                .properties(properties)
                .build();
    }


    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Parent {

        @JsonProperty("database_id")
        private String databaseId;
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Properties {

        @JsonProperty(RequiredColumn.PROBLEM_SITE)
        private NotionRequestProperty.Select problemSite;

        @JsonProperty(RequiredColumn.PROBLEM_NUMBER)
        private NotionRequestProperty.Number problemNumber;

        @JsonProperty(RequiredColumn.PROBLEM_TITLE)
        private NotionRequestProperty.Title problemTitle;

        @JsonProperty(RequiredColumn.PROBLEM_URL)
        private NotionRequestProperty.Url problemUrl;

    }
}
