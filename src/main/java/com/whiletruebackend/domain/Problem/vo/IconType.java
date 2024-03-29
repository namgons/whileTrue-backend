package com.whiletruebackend.domain.Problem.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IconType {

    EXTERNAL("external"),
    EMOJI("emoji"),
    FILE("file");

    private String name;
}
