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
    public Long id;

    public String userId;
    public String notionApiKey;
    private String tokenType;

    private String accessToken;
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder
    public Member(String userId, String notionApiKey, String tokenType) {
        this.userId = userId;
        this.notionApiKey = notionApiKey;
        this.tokenType = tokenType;
    }

    public void updateProfile(Profile profile) {
        this.profile = profile;
        profile.updateMember(this);
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
