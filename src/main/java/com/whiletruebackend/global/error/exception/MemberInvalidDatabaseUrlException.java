package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class MemberInvalidDatabaseUrlException extends CustomException {

    public static final CustomException EXCEPTION = new MemberInvalidDatabaseUrlException();

    public MemberInvalidDatabaseUrlException() {
        super(GlobalErrorCode.MEMBER_INVALID_DATABASE_URL);
    }
}
