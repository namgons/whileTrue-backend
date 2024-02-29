package com.whiletruebackend.domain.Problem.vo;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Problem {

    private SiteType siteType;
    private String level;
    private String number;
    private String title;
    private String url;

    public static Problem fromProblemPage(ProblemPage problemPage) {
        return Problem.builder()
                .siteType(problemPage.getSiteType())
                .level(problemPage.getLevel())
                .number(problemPage.getNumber())
                .title(problemPage.getTitle())
                .url(problemPage.getUrl()).build();
    }
}
