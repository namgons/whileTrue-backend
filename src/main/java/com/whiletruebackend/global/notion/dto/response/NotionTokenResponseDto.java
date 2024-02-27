package com.whiletruebackend.global.notion.dto.response;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.entity.NotionSpace;
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

    public Member toMemberEntity() {
        return Member.builder()
                .notionApiKey(notionApiKey)
                .tokenType(tokenType)
                .userId(notionUserId)
                .userName(notionUserName)
                .avatarUrl(notionUserAvatarUrl).build();
    }

    public NotionSpace toNotionSpaceEntity() {
        return NotionSpace.builder()
                .workspaceName(workspaceName)
                .workspaceIcon(workspaceIcon)
                .workspaceId(workspaceId)
                .build();
    }

}
