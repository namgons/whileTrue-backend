package com.whiletruebackend.domain.Problem.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SiteType {

    BOJ("백준"),
    PROGRAMMERS("프로그래머스"),
    SWEA("SWEA");

    private String value;
}
