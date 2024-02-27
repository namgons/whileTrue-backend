package com.whiletruebackend.domain.Problem.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IconType {

    EXTERNAL("external"),
    EMOJI("emoji"),
    FILE("file");

    private String value;

    @JsonCreator
    public static IconType from(String s) {
        return IconType.valueOf(s.toUpperCase());
    }
}
