package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class MemberInvalidDatabaseFormatException extends CustomException {

    public static final CustomException EXCEPTION = new MemberInvalidDatabaseFormatException();

    public MemberInvalidDatabaseFormatException() {
        super(GlobalErrorCode.MEMBER_INVALID_DATABASE_FORMAT);
    }
}