package com.whiletruebackend.domain.Problem.vo;

import com.whiletruebackend.global.utils.ProblemDataConverter;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProblemPage {

    private SiteType siteType;
    private String level;
    private String number;
    private String title;
    private String url;
    private IconType iconType;
    private String iconSrc;

    public static ProblemPage fromProblem(Problem problem) {
        return ProblemPage.builder()
                .siteType(problem.getSiteType())
                .number(problem.getNumber())
                .title(problem.getTitle())
                .url(problem.getUrl())
                .iconType(ProblemDataConverter.createIconType(problem.getSiteType()))
                .iconSrc(ProblemDataConverter.createIconSrc(problem.getSiteType(), problem.getLevel())).build();
    }
}
