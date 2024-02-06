package com.whiletruebackend.domain.Member.controller;

import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.service.MemberService;
import com.whiletruebackend.global.response.JsonResponse;
import com.whiletruebackend.global.response.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // TODO: ACCESS TOKEN, REFRESH TOKEN 발급하기
    @GetMapping("/oauth/{accessCode}")
    public ResponseEntity<ResponseWrapper<Nullable>> getAccessToken(@PathVariable String accessCode) {
        Long memberId = memberService.requestAccessToken(accessCode);
        return JsonResponse.ok("Access Token과 Refresh Token을 발급했습니다.");
    }

    @GetMapping("/notion-space")
    public ResponseEntity<ResponseWrapper<MemberNotionSpaceResponseDto>> getMemberNotionInformation(
            @AuthenticationPrincipal Member member) {
        MemberNotionSpaceResponseDto memberNotionSpaceDto = memberService.getMemberNotionSpace(member);
        return JsonResponse.ok("사용자의 워크스페이스와 데이터베이스 정보를 가져왔습니다.", memberNotionSpaceDto);
    }

    @PostMapping("/notion-database-id")
    public ResponseEntity<ResponseWrapper<Nullable>> getNotionDatabaseId(
            @AuthenticationPrincipal Member member,
            @RequestBody NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto
    ) {
        memberService.saveNotionDatabaseInfo(member, notionDatabaseIdUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

}
