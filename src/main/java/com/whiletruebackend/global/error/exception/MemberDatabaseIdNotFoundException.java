package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class MemberDatabaseIdNotFoundException extends CustomException {

    public static final CustomException EXCEPTION = new MemberDatabaseIdNotFoundException();

    public MemberDatabaseIdNotFoundException() {
        super(GlobalErrorCode.MEMBER_DATABASE_ID_NOT_FOUND);
    }
}
