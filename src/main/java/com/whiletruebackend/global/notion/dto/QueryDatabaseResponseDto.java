package com.whiletruebackend.global.notion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QueryDatabaseResponseDto {

    private List<NotionPage> results;
}

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class NotionPage {

    private String id;
    private Boolean archived;
    private NotionPageProperty properties;
    private String url;
}

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class NotionPageProperty {

    @JsonProperty("Problem Site")
    private NotionPropertyType.Select problemSite;
    @JsonProperty("Problem Number")
    private NotionPropertyType.Number problemNumber;
    @JsonProperty("Problem Title")
    private NotionPropertyType.Title problemTitle;
    @JsonProperty("URL")
    private NotionPropertyType.Url problemUrl;
}