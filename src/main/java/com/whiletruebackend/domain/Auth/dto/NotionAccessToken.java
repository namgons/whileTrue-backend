package com.whiletruebackend.domain.Auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotionAccessToken {

    private String access_token;
    private String token_type;
    private String bot_id;
    private String workspace_name;
    private String workspace_icon;
    private String workspace_id;
    private NotionOwner owner;
    private String duplicated_template_id;
    private String request_id;

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
    private String avatar_url;
    private String type;

}