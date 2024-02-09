package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthInvalidTokenException extends CustomException {

    public static final CustomException EXCEPTION = new AuthInvalidTokenException();

    public AuthInvalidTokenException() {
        super(GlobalErrorCode.AUTH_INVALID_TOKEN);
    }
}