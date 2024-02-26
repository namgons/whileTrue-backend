package com.whiletruebackend.global.notion.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckDatabaseResponseDto {

    private Boolean checkValid;
    private String databaseId;
    private String databaseIconType;
    private String databaseIconSrc;
    private String databaseTitle;

}
