package com.whiletruebackend.global.notion.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotionDatabaseRequestDto {

    private String notionApiKey;
    private String databaseId;
}
