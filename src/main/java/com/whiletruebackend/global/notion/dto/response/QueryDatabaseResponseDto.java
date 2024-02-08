package com.whiletruebackend.global.notion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QueryDatabaseResponseDto {

    List<NotionPage> results;
    @JsonProperty("next_cursor")
    private String nextCursor;
    @JsonProperty("has_more")
    private Boolean hasMore;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class NotionPage {

        private String id;
        private Boolean archived;
        private NotionPageProperty properties;
        private String url;

        public String getProblemSite() {
            return this.properties.getProblemSite().getSelect().getName();
        }

        public String getProblemNumber() {
            return this.properties.getProblemNumber().getNumber();
        }

        public String getProblemTitle() {
            return this.properties.getProblemTitle().getTitle().get(0).getPlainText();
        }

        public String getProblemUrl() {
            return this.properties.getProblemUrl().getUrl();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class NotionPageProperty {

        @JsonProperty(RequiredColumn.PROBLEM_SITE)
        private NotionPropertyType.Select problemSite;
        @JsonProperty(RequiredColumn.PROBLEM_NUMBER)
        private NotionPropertyType.Number problemNumber;
        @JsonProperty(RequiredColumn.PROBLEM_TITLE)
        private NotionPropertyType.Title problemTitle;
        @JsonProperty(RequiredColumn.PROBLEM_URL)
        private NotionPropertyType.Url problemUrl;
    }
}

