package com.whiletruebackend.domain.Problem.controller;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Problem.dto.response.ProblemListResponseDto;
import com.whiletruebackend.domain.Problem.service.ProblemService;
import com.whiletruebackend.global.response.JsonResponse;
import com.whiletruebackend.global.response.ResponseWrapper;
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
    public ResponseEntity<ResponseWrapper<ProblemListResponseDto>> getProblemList(@AuthenticationPrincipal Member member) {
        ProblemListResponseDto responseDto = problemService.getProblemList(member);
        return JsonResponse.ok("문제 리스트를 가져왔습니다.", responseDto);
    }
}
