package com.whiletruebackend.domain.Member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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