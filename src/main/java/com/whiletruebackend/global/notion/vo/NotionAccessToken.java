package com.whiletruebackend.global.notion.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.entity.NotionSpace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotionAccessToken {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("bot_id")
    private String botId;
    @JsonProperty("workspace_name")
    private String workspaceName;
    @JsonProperty("workspace_icon")
    private String workspaceIcon;
    @JsonProperty("workspace_id")
    private String workspaceId;
    private NotionOwner owner;
    @JsonProperty("duplicated_template_id")
    private String duplicatedTemplateId;
    @JsonProperty("request_id")
    private String requestId;

    public Member toMemberEntity() {
        NotionUser user = this.owner.getUser();
        return Member.builder()
                .userId(user.getId())
                .userName(user.getName())
                .avatarUrl(user.getAvatarUrl())
                .notionApiKey(accessToken)
                .tokenType(tokenType)
                .build();
    }

    public NotionSpace toNotionSpaceEntity() {
        return NotionSpace.builder()
                .workspaceId(workspaceId)
                .workspaceIcon(workspaceIcon)
                .workspaceName(workspaceName)
                .build();
    }
}

@Getter
@NoArgsConstructor
class NotionOwner {

    private String type;
    private NotionUser user;
}

@Getter
@NoArgsConstructor
class NotionUser {

    private String object;
    private String id;
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    private String type;
}