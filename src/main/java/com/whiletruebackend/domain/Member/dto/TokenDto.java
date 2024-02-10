package com.whiletruebackend.domain.Member.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class TokenDto {

    private String accessToken;
    private String refreshToken;

}
