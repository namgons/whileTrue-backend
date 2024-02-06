package com.whiletruebackend.domain.Member.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotionDatabaseIdUpdateRequestDto {

    private String notionDatabaseId;

}
