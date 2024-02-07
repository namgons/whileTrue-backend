package com.whiletruebackend.domain.Member.entity;

import com.whiletruebackend.global.notion.dto.RetrieveDatabaseResponseDto;
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
    private String workspaceIcon;
    private String workspaceName;

    private String databaseId;
    private String databaseIcon;
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

    public void updateWorkspace(String workspaceId, String workspaceIcon, String workspaceName) {
        this.workspaceId = workspaceId;
        this.workspaceIcon = workspaceIcon;
        this.workspaceName = workspaceName;
    }

    public void updateDatabase(RetrieveDatabaseResponseDto retrieveDatabaseResponseDto) {
        this.databaseId = retrieveDatabaseResponseDto.getId();
        this.databaseIcon = retrieveDatabaseResponseDto.getIcon().getEmoji();
        this.databaseTitle = retrieveDatabaseResponseDto.getTitle().get(0).getPlainText();
    }

}
