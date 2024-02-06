package com.whiletruebackend.domain.Member.service;

import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;

public interface MemberService {

    Long requestAccessToken(String accessCode);

    void saveNotionDatabaseInfo(Member member, NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto);

    MemberNotionSpaceResponseDto getMemberNotionSpace(Member member);
}
