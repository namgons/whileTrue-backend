package com.whiletruebackend.domain.Problem.dto.response;

import com.whiletruebackend.domain.Problem.vo.Problem;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ProblemListResponseDto {

    private int totalCount;
    private List<Problem> problemList;

    public static ProblemListResponseDto from(List<Problem> problemList) {
        return ProblemListResponseDto.builder()
                .totalCount(problemList.size())
                .problemList(problemList)
                .build();
    }
}
