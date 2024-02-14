package com.whiletruebackend.global.notion.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.RequiredColumn;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CreatePageRequestDto {

    private Parent parent;
    private Properties properties;

    public static CreatePageRequestDto from(String databaseId, Problem problem) {

        Properties properties = Properties.builder()
                .problemSite(NotionRequestProperty.Select.create(problem.getSiteType()))
                .problemNumber(new NotionRequestProperty.Number(Integer.parseInt(problem.getNumber())))
                .problemTitle(NotionRequestProperty.Title.create(problem.getTitle()))
                .problemUrl(new NotionRequestProperty.Url(problem.getUrl()))
                .build();

        return CreatePageRequestDto.builder()
                .parent(new Parent(databaseId))
                .properties(properties)
                .build();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Parent {

        @JsonProperty("database_id")
        private String databaseId;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class Properties {

        @JsonProperty(RequiredColumn.Name.PROBLEM_SITE)
        private NotionRequestProperty.Select problemSite;
        @JsonProperty(RequiredColumn.Name.PROBLEM_NUMBER)
        private NotionRequestProperty.Number problemNumber;
        @JsonProperty(RequiredColumn.Name.PROBLEM_TITLE)
        private NotionRequestProperty.Title problemTitle;
        @JsonProperty(RequiredColumn.Name.PROBLEM_URL)
        private NotionRequestProperty.Url problemUrl;

    }
}
