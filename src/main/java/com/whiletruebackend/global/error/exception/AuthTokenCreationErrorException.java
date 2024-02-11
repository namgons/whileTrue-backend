package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class AuthTokenCreationErrorException extends CustomException {

    public static final CustomException EXCEPTION = new AuthTokenCreationErrorException();

    public AuthTokenCreationErrorException() {
        super(GlobalErrorCode.AUTH_TOKEN_CREATION_ERROR);
    }
}
