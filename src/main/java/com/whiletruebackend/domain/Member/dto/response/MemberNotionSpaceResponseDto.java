package com.whiletruebackend.domain.Member.dto.response;

import com.whiletruebackend.domain.Member.entity.NotionSpace;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberNotionSpaceResponseDto {

    private String workspaceIcon;
    private String workspaceName;
    private String databaseIcon;
    private String databaseTitle;

    public static MemberNotionSpaceResponseDto from(NotionSpace notionSpace) {
        return MemberNotionSpaceResponseDto.builder()
                .workspaceIcon(notionSpace.getWorkspaceIcon())
                .workspaceName(notionSpace.getWorkspaceName())
                .databaseIcon(notionSpace.getDatabaseIcon())
                .databaseTitle(notionSpace.getDatabaseTitle())
                .build();
    }
}
