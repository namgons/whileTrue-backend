package com.whiletruebackend.domain.Member.dto.response;

import com.whiletruebackend.domain.Member.entity.NotionSpace;
import com.whiletruebackend.domain.Problem.vo.IconType;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class MemberNotionSpaceResponseDto {

    private IconType workspaceIconType;
    private String workspaceIconSrc;
    private String workspaceName;
    private IconType databaseIconType;
    private String databaseIconSrc;
    private String databaseTitle;

    public static MemberNotionSpaceResponseDto from(NotionSpace notionSpace) {
        return MemberNotionSpaceResponseDto.builder()
                .workspaceIconType(notionSpace.getWorkspaceIconType())
                .workspaceIconSrc(notionSpace.getWorkspaceIconSrc())
                .workspaceName(notionSpace.getWorkspaceName())
                .databaseIconType(notionSpace.getDatabaseIconType())
                .databaseIconSrc(notionSpace.getDatabaseIconSrc())
                .databaseTitle(notionSpace.getDatabaseTitle()).build();
    }
}
