package com.whiletruebackend.global.notion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotionDatabaseTitle {

    @JsonProperty("plain_text")
    private String plainText;
}
