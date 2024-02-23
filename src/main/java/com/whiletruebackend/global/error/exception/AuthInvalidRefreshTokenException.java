package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthInvalidRefreshTokenException extends CustomException {

    public static final CustomException EXCEPTION = new AuthInvalidRefreshTokenException();

    public AuthInvalidRefreshTokenException() {
        super(GlobalErrorCode.AUTH_INVALID_REFRESH_TOKEN);
    }
}
