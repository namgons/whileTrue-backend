package com.whiletruebackend.global.notion.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotionTokenResponseDto {

    private String notionApiKey;
    private String tokenType;
    private String workspaceName;
    private String workspaceIcon;
    private String workspaceId;
    private String notionUserId;
    private String notionUserName;
    private String notionUserAvatarUrl;

}
