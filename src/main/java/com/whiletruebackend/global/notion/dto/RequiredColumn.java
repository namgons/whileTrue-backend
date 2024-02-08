package com.whiletruebackend.global.notion.dto;

import lombok.Getter;

@Getter
public class RequiredColumn {

    @Getter
    public static class Name {

        public static final String PROBLEM_SITE = "문제 사이트";
        public static final String PROBLEM_NUMBER = "문제 번호";
        public static final String PROBLEM_TITLE = "문제 제목";
        public static final String PROBLEM_URL = "URL";
    }

    @Getter
    public static class Type {

        public static final String PROBLEM_SITE = "select";
        public static final String PROBLEM_NUMBER = "number";
        public static final String PROBLEM_TITLE = "title";
        public static final String PROBLEM_URL = "url";
    }

}
