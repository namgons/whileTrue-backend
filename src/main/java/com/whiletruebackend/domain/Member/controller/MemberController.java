package com.whiletruebackend.domain.Member.controller;

import com.whiletruebackend.domain.Member.dto.request.NotionDatabaseIdUpdateRequestDto;
import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Member.service.MemberService;
import com.whiletruebackend.global.response.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/oauth/{accessCode}")
    public ResponseEntity<?> getAccessToken(@PathVariable String accessCode) {
        Long memberId = memberService.requestAccessToken(accessCode);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/member")
    public ResponseEntity<ResponseWrapper<Nullable>> getNotionDatabaseId(
            @AuthenticationPrincipal Member member,
            @RequestBody NotionDatabaseIdUpdateRequestDto notionDatabaseIdUpdateRequestDto
    ) {
        memberService.saveNotionDatabaseInfo(member, notionDatabaseIdUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

}
