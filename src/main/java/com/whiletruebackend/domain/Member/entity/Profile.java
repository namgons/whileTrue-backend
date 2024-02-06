package com.whiletruebackend.domain.Member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    private String userId;
    private String userName;
    private String avatarUrl;

    private String workspaceId;
    private String workspaceIcon;
    private String workspaceName;

    private String databaseId;
    private String databaseIcon;
    private String databaseTitle;

    @Builder
    public Profile(String userId, String userName, String avatarUrl, String workspaceId, String workspaceIcon, String workspaceName) {
        this.userId = userId;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.workspaceId = workspaceId;
        this.workspaceIcon = workspaceIcon;
        this.workspaceName = workspaceName;
    }

    public void updateProfile(String userName, String avatarUrl, String workspaceId, String workspaceIcon, String workspaceName) {
        this.userName = userName;
        this.avatarUrl = avatarUrl;
        this.workspaceId = workspaceId;
        this.workspaceIcon = workspaceIcon;
        this.workspaceName = workspaceName;
    }

    public void updateDatabase(String databaseId, String databaseIcon, String databaseTitle) {
        this.databaseId = databaseId;
        this.databaseIcon = databaseIcon;
        this.databaseTitle = databaseTitle;
    }

}
