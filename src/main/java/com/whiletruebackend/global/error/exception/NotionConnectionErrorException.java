package com.whiletruebackend.global.error.exception;

import com.whiletruebackend.global.error.errorCode.GlobalErrorCode;

public class NotionConnectionErrorException extends CustomException {

    public static final CustomException EXCEPTION = new NotionConnectionErrorException();

    public NotionConnectionErrorException() {
        super(GlobalErrorCode.NOTION_CONNECTION_ERROR);
    }
}
