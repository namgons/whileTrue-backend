package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthNoAuthorizationException extends CustomException {

    public static final CustomException EXCEPTION = new AuthNoAuthorizationException();

    public AuthNoAuthorizationException() {
        super(GlobalErrorCode.AUTH_NO_AUTHORIZATION);
    }
}
