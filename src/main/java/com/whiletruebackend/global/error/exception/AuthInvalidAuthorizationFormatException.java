package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthInvalidAuthorizationFormatException extends CustomException {

    public static final CustomException EXCEPTION = new AuthInvalidAuthorizationFormatException();

    public AuthInvalidAuthorizationFormatException() {
        super(GlobalErrorCode.AUTH_INVALID_AUTHORIZATION_FORMAT);
    }
}
