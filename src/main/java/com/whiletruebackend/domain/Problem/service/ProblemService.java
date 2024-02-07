package com.whiletruebackend.domain.Problem.service;

import com.whiletruebackend.domain.Member.entity.Member;
import com.whiletruebackend.domain.Problem.dto.response.ProblemListResponseDto;

public interface ProblemService {

    ProblemListResponseDto getProblemList(Member member);
}
