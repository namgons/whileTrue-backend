package com.whiletruebackend.domain.Problem.vo;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Problem {

    private String problemSiteType;
    private String problemNumber;
    private String problemTitle;
    private String url;
}
