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

    @Column(columnDefinition = "CHAR(36)")
    private String workspaceId;
    private String workspaceIcon;
    private String workspaceName;

    @Column(columnDefinition = "CHAR(36)")
    private String databaseId;
    @Enumerated(value = EnumType.STRING)
    private IconType databaseIconType;
    @Column(columnDefinition = "TEXT")
    private String databaseIconSrc;
    private String databaseTitle;

    @OneToOne(mappedBy = "notionSpace", fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public NotionSpace(String workspaceId, String workspaceIcon, String workspaceName) {
        this.workspaceId = workspaceId;
        this.workspaceIcon = workspaceIcon;
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
        this.workspaceIcon = notionTokenDto.getWorkspaceIcon();
        this.workspaceName = notionTokenDto.getWorkspaceName();
    }
}
