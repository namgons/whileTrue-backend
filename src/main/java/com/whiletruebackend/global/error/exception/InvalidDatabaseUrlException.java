package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class InvalidDatabaseUrlException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidDatabaseUrlException();

    public InvalidDatabaseUrlException() {
        super(GlobalErrorCode.MEMBER_INVALID_DATABASE_URL);
    }
}
