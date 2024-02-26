package com.whiletruebackend.domain.Problem.dto.response;

import com.whiletruebackend.domain.Problem.vo.ProbemPage;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ProblemListResponseDto {

    private int totalCount;
    private List<ProbemPage> problemList;

    public static ProblemListResponseDto from(List<ProbemPage> problemList) {
        return ProblemListResponseDto.builder()
                .totalCount(problemList.size())
                .problemList(problemList)
                .build();
    }
}
