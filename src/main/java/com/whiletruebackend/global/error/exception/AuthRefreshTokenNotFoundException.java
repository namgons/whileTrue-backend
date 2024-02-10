package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthRefreshTokenNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new AuthRefreshTokenNotFoundException();

    public AuthRefreshTokenNotFoundException() {
        super(GlobalErrorCode.AUTH_REFRESH_TOKEN_NOT_FOUND);
    }
}
