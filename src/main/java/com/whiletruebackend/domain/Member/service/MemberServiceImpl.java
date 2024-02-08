package com.whiletruebackend.domain.Member.service;

import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.entity.NotionSpace;
import com.whiletruebackend.domain.Member.repository.MemberRepository;
import com.whiletruebackend.domain.Member.repository.NotionSpaceRepository;
import com.whiletruebackend.global.notion.dto.response.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.service.NotionService;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final NotionSpaceRepository notionSpaceRepository;
    private final NotionService notionService;

    @Override
    @Transactional
    public Long requestAccessToken(String accessCode) {
        NotionAccessToken notionAccessToken = notionService.requestNotionToken(accessCode);
        Member member = saveNotionAccessToken(notionAccessToken);

        if (notionAccessToken.getDuplicatedTemplateId() != null) {
            saveNotionDatabaseInfo(member, notionAccessToken.getDuplicatedTemplateId());
        }

        return member.getId();
    }

    @Override
    public MemberNotionSpaceResponseDto saveNotionDatabaseInfo(Member member,
                                                               NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto) {
        NotionSpace notionSpace = saveNotionDatabaseInfo(member, notionDatabaseIdUpdateRequestDto.getNotionDatabaseId());
        return MemberNotionSpaceResponseDto.from(notionSpace);
    }
    
    @Override
    public MemberNotionSpaceResponseDto getMemberNotionSpace(Member member) {
        return MemberNotionSpaceResponseDto.from(member.getNotionSpace());
    }

    private Member saveNotionAccessToken(NotionAccessToken notionAccessToken) {
        NotionSpace notionSpace = notionAccessToken.toNotionSpaceEntity();
        Member member = notionAccessToken.toMemberEntity();
        member.updateNotionSpace(notionSpace);

        notionSpaceRepository.save(notionSpace);
        return memberRepository.save(member);
    }

    private NotionSpace saveNotionDatabaseInfo(Member member, String databaseId) {
        RetrieveDatabaseResponseDto retrieveDatabaseResponseDto =
                notionService.retrieveDatabase(member.getNotionApiKey(), databaseId);

        NotionSpace notionSpace = member.getNotionSpace();
        notionSpace.updateDatabase(retrieveDatabaseResponseDto);
        return notionSpaceRepository.save(notionSpace);
    }
}
