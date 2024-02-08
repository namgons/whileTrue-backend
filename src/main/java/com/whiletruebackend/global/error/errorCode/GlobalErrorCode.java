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

    INVALID_DATABASE_URL(BAD_REQUEST, "MEMBER-400-1", "주어진 URL이 적합하지 않습니다"),
    INVALID_MEMBER_DATABASE_FORMAT(BAD_REQUEST, "MEMBER-400-1", "사용자의 데이터베이스 포맷이 적절하지 않습니다.");

    private HttpStatus status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
