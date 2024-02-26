package com.whiletruebackend.domain.Problem.dto.response;

import com.whiletruebackend.global.notion.dto.response.CheckProblemResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProblemExistenceResponseDto {

    private boolean isProblemExists;

    public static ProblemExistenceResponseDto from(CheckProblemResponseDto checkProblemResponseDto) {
        return new ProblemExistenceResponseDto(checkProblemResponseDto.getIsExists());
    }
}
