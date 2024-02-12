package com.whiletruebackend.domain.Member.service;

import com.whiletruebackend.domain.Member.dto.TokenDto;
import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.entity.NotionSpace;
import com.whiletruebackend.domain.Member.repository.MemberRepository;
import com.whiletruebackend.domain.Member.repository.NotionSpaceRepository;
import com.whiletruebackend.global.error.exception.InvalidDatabaseUrlException;
import com.whiletruebackend.global.error.exception.InvalidMemberDatabaseFormatException;
import com.whiletruebackend.global.notion.dto.RequiredColumn;
import com.whiletruebackend.global.notion.dto.response.RetrieveDatabaseResponseDto;
import com.whiletruebackend.global.notion.service.NotionService;
import com.whiletruebackend.global.notion.vo.NotionAccessToken;
import com.whiletruebackend.global.utils.AuthHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final NotionSpaceRepository notionSpaceRepository;
    private final NotionService notionService;

    private final AuthHelper authHelper;

    @Override
    @Transactional
    public TokenDto requestAccessToken(String accessCode) {
        NotionAccessToken notionAccessToken = notionService.requestNotionToken(accessCode);
        Member member = saveNotionAccessToken(notionAccessToken);

        return new TokenDto(
                authHelper.createAccessToken(member),
                authHelper.createRefreshToken(member)
        );
    }

    @Override
    @Transactional
    public MemberNotionSpaceResponseDto saveNotionDatabaseInfo(Member member,
                                                               NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto) {
        String databaseUrl = notionDatabaseIdUpdateRequestDto.getNotionDatabaseUrl();
        String databaseId = parseDatabaseId(databaseUrl);

        RetrieveDatabaseResponseDto retrieveDatabaseResponseDto = notionService.retrieveDatabase(member.getNotionApiKey(), databaseId);

        checkDatabaseColumn(retrieveDatabaseResponseDto);

        NotionSpace notionSpace = member.getNotionSpace();
        notionSpace.updateDatabase(retrieveDatabaseResponseDto);
        notionSpaceRepository.save(notionSpace);
        
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
        Member savedMember = memberRepository.save(member);
        notionSpaceRepository.save(notionSpace);
        return savedMember;
    }

    private void checkDatabaseColumn(RetrieveDatabaseResponseDto retrieveDatabaseResponseDto) {
        if (retrieveDatabaseResponseDto.getProblemSiteProperty() == null
                || retrieveDatabaseResponseDto.getProblemNumberProperty() == null
                || retrieveDatabaseResponseDto.getProblemTitleProperty() == null
                || retrieveDatabaseResponseDto.getProblemUrlProperty() == null
                || !retrieveDatabaseResponseDto.getProblemSiteProperty().getType().equals(RequiredColumn.Type.PROBLEM_SITE)
                || !retrieveDatabaseResponseDto.getProblemNumberProperty().getType().equals(RequiredColumn.Type.PROBLEM_NUMBER)
                || !retrieveDatabaseResponseDto.getProblemTitleProperty().getType().equals(RequiredColumn.Type.PROBLEM_TITLE)
                || !retrieveDatabaseResponseDto.getProblemUrlProperty().getType().equals(RequiredColumn.Type.PROBLEM_URL)
        ) {
            throw InvalidMemberDatabaseFormatException.EXCEPTION;
        }
    }

    private String parseDatabaseId(String databaseUrl) {
        String pattern = "https:\\/\\/www\\.notion\\.so\\/(.+?)\\/(.+?)\\?v=(.+)";
        Matcher m = Pattern.compile(pattern).matcher(databaseUrl);

        if (!m.matches()) {
            throw InvalidDatabaseUrlException.EXCEPTION;
        }
        return m.group(2);
    }
}
