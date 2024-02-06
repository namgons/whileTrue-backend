package com.whiletruebackend.domain.Member.dto.response;

import com.whiletruebackend.domain.Member.entity.Profile;
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

    public static MemberNotionSpaceResponseDto from(Profile profile) {
        return MemberNotionSpaceResponseDto.builder()
                .workspaceIcon(profile.getWorkspaceIcon())
                .workspaceName(profile.getWorkspaceName())
                .databaseIcon(profile.getDatabaseIcon())
                .databaseTitle(profile.getDatabaseTitle())
                .build();
    }
}
