package com.whiletruebackend.domain.Member.entity;

import com.whiletruebackend.domain.Problem.vo.IconType;
import com.whiletruebackend.global.notion.dto.response.CheckDatabaseResponseDto;
import com.whiletruebackend.global.notion.dto.response.NotionTokenResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotionSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notion_space_id")
    private Long id;

    private String workspaceId;
    private IconType workspaceIconType;
    private String workspaceIconSrc;
    private String workspaceName;

    private String databaseId;
    private IconType databaseIconType;
    private String databaseIconSrc;
    private String databaseTitle;

    @OneToOne(mappedBy = "notionSpace", fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public NotionSpace(String workspaceId, IconType workspaceIconType, String workspaceIconSrc, String workspaceName) {
        this.workspaceId = workspaceId;
        this.workspaceIconType = workspaceIconType;
        this.workspaceIconSrc = workspaceIconSrc;
        this.workspaceName = workspaceName;
    }

    public void updateMember(Member member) {
        this.member = member;
    }

    public void updateDatabase(CheckDatabaseResponseDto databaseResponseDto) {
        this.databaseId = databaseResponseDto.getDatabaseId();
        this.databaseIconType = databaseResponseDto.getDatabaseIconType();
        this.databaseIconSrc = databaseResponseDto.getDatabaseIconSrc();
        this.databaseTitle = databaseResponseDto.getDatabaseTitle();
    }

    public void updateNotionSpace(NotionTokenResponseDto notionTokenDto) {
        this.workspaceId = notionTokenDto.getWorkspaceId();
        this.workspaceIconType = notionTokenDto.getWorkspaceIconType();
        this.workspaceIconSrc = notionTokenDto.getWorkspaceIconSrc();
        this.workspaceName = notionTokenDto.getWorkspaceName();
    }
}
