package com.whiletruebackend.domain.Member.controller;

import com.whiletruebackend.domain.Member.dto.TokenDto;
import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.dto.response.MemberNotionSpaceResponseDto;
import com.whiletruebackend.domain.Member.dto.response.MemberTokenResponseDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.service.MemberService;
import com.whiletruebackend.global.response.JsonResponse;
import com.whiletruebackend.global.response.ResponseWrapper;
import com.whiletruebackend.global.utils.AuthHelper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final AuthHelper authHelper;

    @GetMapping("/oauth/{accessCode}")
    public ResponseEntity<ResponseWrapper<MemberTokenResponseDto>> getAccessToken(
            @PathVariable String accessCode, HttpServletResponse response) {
        TokenDto tokenDto = memberService.requestAccessToken(accessCode);
        Cookie cookie = authHelper.createCookie(tokenDto.getRefreshToken());
        response.addCookie(cookie);
        return JsonResponse.ok("Access Token과 Refresh Token을 발급했습니다.", new MemberTokenResponseDto(tokenDto.getAccessToken()));
    }

    @GetMapping("/auth/token/refresh")
    public ResponseEntity<ResponseWrapper<MemberTokenResponseDto>> refreshToken(
            @CookieValue(name = "refresh-token", required = false) String refreshToken, HttpServletResponse response) {
        TokenDto tokenDto = authHelper.refreshToken(refreshToken);
        Cookie cookie = authHelper.createCookie(tokenDto.getRefreshToken());
        response.addCookie(cookie);
        return JsonResponse.ok("Access Token을 새로 발급했습니다.", new MemberTokenResponseDto(tokenDto.getAccessToken()));
    }

    @GetMapping("/notion-space")
    public ResponseEntity<ResponseWrapper<MemberNotionSpaceResponseDto>> getMemberNotionInformation(
            @AuthenticationPrincipal Member member) {
        MemberNotionSpaceResponseDto memberNotionSpaceResponseDto = memberService.getMemberNotionSpace(member);
        return JsonResponse.ok("사용자의 워크스페이스와 데이터베이스 정보를 가져왔습니다.", memberNotionSpaceResponseDto);
    }

    @PostMapping("/notion-database-id")
    public ResponseEntity<ResponseWrapper<MemberNotionSpaceResponseDto>> getNotionDatabaseId(
            @AuthenticationPrincipal Member member,
            @RequestBody NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto) {
        MemberNotionSpaceResponseDto memberNotionSpaceResponseDto =
                memberService.saveNotionDatabaseInfo(member, notionDatabaseIdUpdateRequestDto);
        return JsonResponse.ok("데이터베이스 정보를 저장했습니다. 사용자의 워크스페이스와 데이터베이스 정보를 가져왔습니다.", memberNotionSpaceResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponseWrapper<Nullable>> deleteMember(
            @CookieValue(name = "refresh-token", required = false) String refreshToken,
            @AuthenticationPrincipal Member member, HttpServletResponse response) {
        memberService.deleteMember(member);
        Cookie cookie = authHelper.deleteCookie(refreshToken);
        response.addCookie(cookie);
        return JsonResponse.ok("사용자를 삭제했습니다.");
    }
}
