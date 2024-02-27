package com.whiletruebackend.domain.Problem.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SiteType {

    BOJ("백준"),
    PROGRAMMERS("프로그래머스"),
    SWEA("SWEA");

    private String value;

    @JsonCreator
    public static SiteType from(String s) {
        return Arrays.stream(SiteType.values())
                .filter(siteType -> siteType.getValue().equals(s))
                .findAny()
                .orElseThrow();
    }
}
