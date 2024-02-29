package com.whiletruebackend.domain.Problem.dto.response;

import com.whiletruebackend.domain.Problem.vo.Problem;
import com.whiletruebackend.global.notion.dto.response.ProblemPageListResponseDto;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ProblemListResponseDto {

    private int totalCount;
    private List<Problem> problemList;

    public static ProblemListResponseDto from(ProblemPageListResponseDto problemPageListResponseDto) {
        return ProblemListResponseDto.builder()
                .totalCount(problemPageListResponseDto.getProblemPageList().size())
                .problemList(problemPageListResponseDto.getProblemPageList().stream().map(Problem::fromProblemPage).toList())
                .build();
    }
}
