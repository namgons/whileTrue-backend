package com.whiletruebackend.domain.Member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String userName;
    private String avatarUrl;

    private String notionApiKey;
    private String tokenType;

    private String accessToken;
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notion_space_id")
    private NotionSpace notionSpace;

    @Builder
    public Member(String userId, String userName, String avatarUrl, String notionApiKey, String tokenType) {
        this.userId = userId;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.notionApiKey = notionApiKey;
        this.tokenType = tokenType;
    }

    public void updateNotionSpace(NotionSpace notionSpace) {
        this.notionSpace = notionSpace;
        notionSpace.updateMember(this);
    }

    public void updateNotionApiKey(String notionApiKey) {
        this.notionApiKey = notionApiKey;
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
