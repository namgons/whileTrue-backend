package com.whiletruebackend.domain.Problem.vo;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProblemPage {

    private String siteType;
    private String number;
    private String title;
    private String url;
    private String iconType;
    private String iconSrc;
}
