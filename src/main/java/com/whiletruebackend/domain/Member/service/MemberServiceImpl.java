package com.whiletruebackend.domain.Member.service;

import com.whiletruebackend.domain.Member.dto.TokenDto;
import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.entity.NotionSpace;
import com.whiletruebackend.domain.Member.repository.MemberRepository;
import com.whiletruebackend.domain.Member.repository.NotionSpaceRepository;
import com.whiletruebackend.global.error.exception.MemberDatabaseNotFoundException;
import com.whiletruebackend.global.error.exception.MemberInvalidDatabaseFormatException;
import com.whiletruebackend.global.error.exception.MemberInvalidDatabaseUrlException;
import com.whiletruebackend.global.notion.dto.response.CheckDatabaseResponseDto;
import com.whiletruebackend.global.notion.dto.response.NotionTokenResponseDto;
import com.whiletruebackend.global.notion.service.NotionService;
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
        NotionTokenResponseDto notionTokenDto = notionService.requestNotionToken(accessCode);

        Member member = memberRepository.findByUserId(notionTokenDto.getNotionUserId());

        if (member != null) {
            member.updateMember(notionTokenDto);
            member.getNotionSpace().updateNotionSpace(notionTokenDto);
        } else {
            member = notionTokenDto.toMemberEntity();
            member.updateNotionSpace(notionTokenDto.toNotionSpaceEntity());
        }

        Member savedMember = memberRepository.save(member);
        notionSpaceRepository.save(member.getNotionSpace());

        return new TokenDto(
                authHelper.createAccessToken(savedMember),
                authHelper.createRefreshToken(savedMember)
        );
    }

    @Override
    @Transactional
    public MemberNotionSpaceResponseDto saveNotionDatabaseInfo(Member member,
                                                               NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto) {
        String databaseId = parseDatabaseId(notionDatabaseIdUpdateRequestDto.getNotionDatabaseUrl());

        CheckDatabaseResponseDto databaseResponseDto = notionService.checkDatabase(member.getNotionApiKey(), databaseId);

        if (databaseResponseDto.getValidCheck().equals("INVALID")) {
            throw MemberInvalidDatabaseFormatException.EXCEPTION;
        } else if (databaseResponseDto.getValidCheck().equals("NOT_FOUND")) {
            throw MemberDatabaseNotFoundException.EXCEPTION;
        }

        NotionSpace notionSpace = member.getNotionSpace();
        notionSpace.updateDatabase(databaseResponseDto);
        notionSpaceRepository.save(notionSpace);

        return MemberNotionSpaceResponseDto.from(notionSpace);
    }

    @Override
    public MemberNotionSpaceResponseDto getMemberNotionSpace(Member member) {
        return MemberNotionSpaceResponseDto.from(member.getNotionSpace());
    }

    @Override
    @Transactional
    public void deleteMember(Member member) {
        NotionSpace notionSpace = member.getNotionSpace();
        memberRepository.delete(member);
        notionSpaceRepository.delete(notionSpace);
    }

    private String parseDatabaseId(String databaseUrl) {
        String pattern = "https:\\/\\/www\\.notion\\.so\\/(.+?)\\/(.+?)\\?v=(.+)";
        Matcher m = Pattern.compile(pattern).matcher(databaseUrl);

        if (!m.matches()) {
            throw MemberInvalidDatabaseUrlException.EXCEPTION;
        }

        String databaseId = m.group(2);
        if (databaseId.length() != 32) {
            throw MemberInvalidDatabaseUrlException.EXCEPTION;
        }

        return databaseId;
    }
}
