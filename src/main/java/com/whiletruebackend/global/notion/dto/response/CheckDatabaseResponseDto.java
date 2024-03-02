package com.whiletruebackend.global.notion.dto.response;

import com.whiletruebackend.domain.Problem.vo.IconType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckDatabaseResponseDto {

    private String validCheck;
    private String databaseId;
    private IconType databaseIconType;
    private String databaseIconSrc;
    private String databaseTitle;

}
