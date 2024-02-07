package com.whiletruebackend.global.notion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotionDatabase {

    private String object;
    private String id;
    private NotionIcon icon;
    private String url;
    private List<NotionTitle> title;
    private NotionDBProperties properties;
}

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class NotionDBProperties {

    @JsonProperty("Problem Number")
    private Property problemNumber;
    @JsonProperty("Problem Title")
    private Property problemTitle;
    @JsonProperty("URL")
    private Property problemUrl;
    @JsonProperty("생성 날짜")
    private Property createdAt;
}

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class Property {

    private String id;
    private String name;
    private String type;
}