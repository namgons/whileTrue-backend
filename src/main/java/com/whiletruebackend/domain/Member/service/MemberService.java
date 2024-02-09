package com.whiletruebackend.domain.Member.service;

import com.whiletruebackend.domain.Member.dto.Token;
import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;

public interface MemberService {

    Token requestAccessToken(String accessCode);

    MemberNotionSpaceResponseDto saveNotionDatabaseInfo(Member member, NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto);

    MemberNotionSpaceResponseDto getMemberNotionSpace(Member member);
}
