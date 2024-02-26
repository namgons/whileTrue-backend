package com.whiletruebackend.global.notion.dto.request;

import com.whiletruebackend.domain.Problem.vo.Problem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProblemRequestDto {

    private String notionApiKey;
    private String databaseId;
    private Problem problem;

}
