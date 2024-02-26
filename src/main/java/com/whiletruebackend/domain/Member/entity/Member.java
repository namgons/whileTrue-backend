package com.whiletruebackend.domain.Member.entity;

import com.whiletruebackend.global.notion.dto.response.NotionTokenResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @UuidGenerator
    @Column(name = "member_id")
    private String id;

    private String userId;
    private String userName;
    private String avatarUrl;

    private String notionApiKey;
    private String tokenType;

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

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void updateMember(NotionTokenResponseDto notionTokenDto) {
        this.userName = notionTokenDto.getNotionUserName();
        this.avatarUrl = notionTokenDto.getNotionUserAvatarUrl();
        this.notionApiKey = notionTokenDto.getNotionApiKey();
        this.tokenType = notionTokenDto.getTokenType();
    }
}
