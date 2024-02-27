package com.whiletruebackend.global.notion.dto.response;

import com.whiletruebackend.domain.Problem.vo.ProblemPage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemPageListResponseDto {

    private Boolean validCheck;
    private List<ProblemPage> problemPageList;
}
