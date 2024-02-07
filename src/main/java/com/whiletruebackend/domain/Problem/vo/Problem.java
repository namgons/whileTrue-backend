package com.whiletruebackend.domain.Problem.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Problem {

    private ProblemSiteType problemType;
    private String problemNumber;
    private String problemTitle;
    private String url;
}
