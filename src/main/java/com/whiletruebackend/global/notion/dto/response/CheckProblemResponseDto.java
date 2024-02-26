package com.whiletruebackend.global.notion.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckProblemResponseDto {

    private Boolean validCheck;
    private Boolean isExists;

}
