package com.whiletruebackend.domain.Problem.dto.request;

import com.whiletruebackend.domain.Problem.vo.ProblemPage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemRequestDto {

    private ProblemPage problemPage;
}
