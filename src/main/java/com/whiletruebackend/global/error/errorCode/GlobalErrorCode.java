package com.whiletruebackend.global.error.errorCode;

import com.whiletruebackend.global.error.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {

    /**
     * 예시
     */
    EXAMPLE_NOT_FOUND(NOT_FOUND, "EXAMPLE-404-1", "예시를 찾을 수 없는 오류입니다."),

    CUSTOM_INTERNAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "GLOBAL-500-1", "서버 오류. 관리자에게 문의 부탁드립니다."),
    NOTION_CONNECTION_ERROR(INTERNAL_SERVER_ERROR, "GLOBAL-500-2", "Notion과의 통신에 오류가 있습니다."),

    /* Member */

    MEMBER_INVALID_DATABASE_URL(BAD_REQUEST, "MEMBER-400-1", "주어진 URL이 적합하지 않습니다"),
    MEMBER_INVALID_DATABASE_FORMAT(BAD_REQUEST, "MEMBER-400-2", "사용자의 데이터베이스 포맷이 적절하지 않습니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "MEMBER-404-1", "주어진 ID에 해당하는 Member가 없습니다."),
    MEMBER_DATABASE_ID_NOT_FOUND(NOT_FOUND, "MEMBER-404-2", "해당 Member의 Database ID가 없습니다."),
    MEMBER_DATABASE_NOT_FOUND(NOT_FOUND, "MEMBER-404-3", "해당 ID의 데이터베이스를 찾을 수 없습니다"),

    /* Auth */

    AUTH_NO_AUTHORIZATION(FORBIDDEN, "AUTH-403-1", "Header에 Authorization Code가 없습니다."),
    AUTH_INVALID_AUTHORIZATION_FORMAT(FORBIDDEN, "AUTH-403-2", "Header에서 Authorizaion Code가 Bearer로 시작하지 않습니다."),
    AUTH_INVALID_TOKEN(FORBIDDEN, "AUTH-403-3", "토큰이 유효하지 않습니다."),
    AUTH_TOKEN_EXPIRED(UNAUTHORIZED, "AUTH-401-1", "토큰이 만료되었습니다."),
    AUTH_REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "AUTH-404-1", "Cookie에 Refresh Token이 없습니다."),
    AUTH_REFRESH_TOKEN_EXPIRED(UNAUTHORIZED, "AUTH-401-2", "Refresh Token이 만료되었습니다."),
    AUTH_INVALID_REFRESH_TOKEN(FORBIDDEN, "AUTH-403-4", "저장된 Refresh Token과 일치하지 않습니다."),
    AUTH_TOKEN_CREATION_ERROR(INTERNAL_SERVER_ERROR, "AUTH-500-1", "Token 생성하는 데에 오류가 있습니다.");


    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
