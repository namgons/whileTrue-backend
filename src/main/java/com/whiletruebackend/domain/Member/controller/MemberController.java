package com.whiletruebackend.domain.Member.controller;

import com.whiletruebackend.domain.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/oauth/{accessCode}")
    public ResponseEntity<?> getAccessToken(@PathVariable String accessCode) {
        Long memberId = memberService.requestAccessToken(accessCode);
        return ResponseEntity.ok().build();
    }
}
