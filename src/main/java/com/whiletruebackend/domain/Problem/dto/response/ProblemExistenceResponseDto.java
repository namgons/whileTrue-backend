package com.whiletruebackend.domain.Problem.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProblemExistenceResponseDto {

    private boolean isProblemExits;

    public static ProblemExistenceResponseDto from(boolean isProblemExits) {
        return new ProblemExistenceResponseDto(isProblemExits);
    }
}