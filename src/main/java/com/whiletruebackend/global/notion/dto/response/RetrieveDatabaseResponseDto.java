package com.whiletruebackend.global.notion.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RetrieveDatabaseResponseDto {

    private String id;
    private NotionPropertyType.Icon icon;
    private String url;
    private List<NotionPropertyType.Text> title;
    private NotionDatabaseProperties properties;
    private Boolean archived;
    @JsonProperty("public_url")
    private String publicUrl;
    
    public String getProblemSiteType() {
        return this.properties.getProblemSite().getType();
    }

    public String getProblemSiteName() {
        return this.properties.getProblemSite().getName();
    }

    public String getProblemNumberType() {
        return this.properties.getProblemNumber().getType();
    }

    public String getProblemNumberName() {
        return this.properties.getProblemNumber().getName();
    }

    public String getProblemTitleType() {
        return this.properties.getProblemTitle().getType();
    }

    public String getProblemTitleName() {
        return this.properties.getProblemTitle().getName();
    }

    public String getProblemUrlType() {
        return this.properties.getProblemUrl().getType();
    }

    public String getProblemUrlName() {
        return this.properties.getProblemUrl().getName();
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class NotionDatabaseProperties {

        @JsonProperty(RequiredColumn.PROBLEM_SITE)
        private ColumnProperty problemSite;
        @JsonProperty(RequiredColumn.PROBLEM_NUMBER)
        private ColumnProperty problemNumber;
        @JsonProperty(RequiredColumn.PROBLEM_TITLE)
        private ColumnProperty problemTitle;
        @JsonProperty(RequiredColumn.PROBLEM_URL)
        private ColumnProperty problemUrl;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ColumnProperty {

        private String id;
        private String name;
        private String type;
    }
}

