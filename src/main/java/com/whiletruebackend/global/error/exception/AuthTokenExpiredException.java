package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthTokenExpiredException extends CustomException {

    public static final CustomException EXCEPTION = new AuthTokenExpiredException();

    public AuthTokenExpiredException() {
        super(GlobalErrorCode.AUTH_TOKEN_EXPIRED);
    }
}
