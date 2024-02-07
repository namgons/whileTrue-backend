package com.whiletruebackend.global.notion.dto;

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
}

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class NotionDatabaseProperties {

    @JsonProperty("Problem Site")
    private ColumnProperty problemSite;
    @JsonProperty("Problem Number")
    private ColumnProperty problemNumber;
    @JsonProperty("Problem Title")
    private ColumnProperty problemTitle;
    @JsonProperty("URL")
    private ColumnProperty problemUrl;
}

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class ColumnProperty {

    private String id;
    private String name;
    private String type;
}