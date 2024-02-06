package com.whiletruebackend.domain.Member.service;

import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.entity.NotionSpace;
import com.whiletruebackend.domain.Member.repository.MemberRepository;
import com.whiletruebackend.domain.Member.repository.ProfileRepository;
import com.whiletruebackend.global.notion.dto.NotionAccessToken;
import com.whiletruebackend.global.notion.dto.NotionDatabase;
import com.whiletruebackend.global.notion.service.NotionService;
import com.whiletruebackend.global.utils.WebClientUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    private final NotionService notionService;

    @Value("${oauth.client_id}")
    private String OAUTH_CLIENT_ID;

    @Value("${oauth.client_secret}")
    private String OAUTH_CLIENT_SECRET;

    @Value("${oauth.redirect_uri}")
    private String REDIRECT_URI;

    private final String NotionTokenEndPoint = "https://api.notion.com/v1/oauth/token";

    @Override
    @Transactional
    public Long requestAccessToken(String accessCode) {

        String encoded = Base64.getEncoder().encodeToString(String.format("%s:%s", OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET).getBytes());
        WebClient webClient = WebClientUtils.createTokenRequestWebClient(NotionTokenEndPoint, encoded);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", accessCode);
        formData.add("redirect_uri", REDIRECT_URI);

        NotionAccessToken notionAccessToken = webClient.post()
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(NotionAccessToken.class)
                .block();

        return saveNotionAccessToken(notionAccessToken);
    }

    @Override
    public void saveNotionDatabaseInfo(Member member, NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto) {
        NotionDatabase notionDatabase = notionService.retrieveDatabase(member.getNotionApiKey(),
                                                                       notionDatabaseIdUpdateRequestDto.getNotionDatabaseId());

        member.getNotionSpace().updateDatabase(notionDatabase);
    }

    @Override
    public MemberNotionSpaceResponseDto getMemberNotionSpace(Member member) {
        return MemberNotionSpaceResponseDto.from(member.getNotionSpace());

    }

    private Long saveNotionAccessToken(NotionAccessToken notionAccessToken) {
        NotionSpace notionSpace = notionAccessToken.toNotionSpaceEntity();
        Member member = notionAccessToken.toMemberEntity();
        member.updateNotionSpace(notionSpace);

        profileRepository.save(notionSpace);
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }
}
