package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthRefreshTokenExpiredException extends CustomException {

    public static final CustomException EXCEPTION = new AuthRefreshTokenExpiredException();

    public AuthRefreshTokenExpiredException() {
        super(GlobalErrorCode.AUTH_REFRESH_TOKEN_EXPIRED);
    }
}
