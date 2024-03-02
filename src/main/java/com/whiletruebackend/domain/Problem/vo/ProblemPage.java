package com.whiletruebackend.domain.Problem.vo;

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

}
