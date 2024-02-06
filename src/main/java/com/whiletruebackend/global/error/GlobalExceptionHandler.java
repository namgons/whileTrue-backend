package com.whiletruebackend.global.error;

import com.whiletruebackend.global.error.dto.ErrorReason;
import com.whiletruebackend.global.error.dto.ErrorResponse;
import com.whiletruebackend.global.error.errorCode.BaseErrorCode;
import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;
import com.whiletruebackend.global.error.exception.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class) // business Exception
    public ResponseEntity<ErrorResponse> handleBusinessException(CustomException e, HttpServletRequest request) {
        BaseErrorCode code = e.getErrorCode();
        ErrorReason errorReason = code.getErrorReason();
        return ResponseEntity.status(errorReason.getStatus())
                .body(ErrorResponse.of(errorReason, request.getRequestURL().toString()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        GlobalErrorCode errorCode = GlobalErrorCode.CUSTOM_INTERNAL_SERVER_ERROR;
        ErrorReason errorReason = errorCode.getErrorReason();
        return ResponseEntity.status(errorCode.getStatus())
                .body(ErrorResponse.of(errorReason, request.getRequestURL().toString()));
    }

}
