package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class InvalidMemberDatabaseFormatException extends CustomException {

    public static final CustomException EXCEPTION = new InvalidMemberDatabaseFormatException();

    public InvalidMemberDatabaseFormatException() {
        super(GlobalErrorCode.MEMBER_INVALID_DATABASE_FORMAT);
    }
}