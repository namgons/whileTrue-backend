package com.whiletruebackend.global.notion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whiletruebackend.global.notion.dto.RequiredColumn;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RetrieveDatabaseResponseDto {

    private String id;
    private NotionPropertyType.Icon icon;
    private String url;
    private List<NotionPropertyType.Text> title;
    private NotionDatabaseProperties properties;
    private Boolean archived;
    @JsonProperty("public_url")
    private String publicUrl;

    public ColumnProperty getProblemSiteProperty() {
        return this.properties.getProblemSite();
    }

    public ColumnProperty getProblemNumberProperty() {
        return this.properties.getProblemNumber();
    }

    public ColumnProperty getProblemTitleProperty() {
        return this.properties.getProblemTitle();
    }

    public ColumnProperty getProblemUrlProperty() {
        return this.properties.getProblemUrl();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public static class NotionDatabaseProperties {

        @JsonProperty(RequiredColumn.Name.PROBLEM_SITE)
        private ColumnProperty problemSite;
        @JsonProperty(RequiredColumn.Name.PROBLEM_NUMBER)
        private ColumnProperty problemNumber;
        @JsonProperty(RequiredColumn.Name.PROBLEM_TITLE)
        private ColumnProperty problemTitle;
        @JsonProperty(RequiredColumn.Name.PROBLEM_URL)
        private ColumnProperty problemUrl;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public static class ColumnProperty {

        private String id;
        private String name;
        private String type;
    }
}

