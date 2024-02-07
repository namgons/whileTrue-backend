package com.whiletruebackend.domain.Problem.controller;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    public ResponseEntity<?> getProblemList(@AuthenticationPrincipal Member member) {
        problemService.getProblemList(member);
        return ResponseEntity.ok().build();
    }
}
