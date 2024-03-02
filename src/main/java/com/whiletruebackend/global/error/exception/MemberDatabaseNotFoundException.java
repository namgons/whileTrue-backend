package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class MemberDatabaseNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new MemberDatabaseNotFoundException();

    public MemberDatabaseNotFoundException() {
        super(GlobalErrorCode.MEMBER_DATABASE_NOT_FOUND);
    }
}