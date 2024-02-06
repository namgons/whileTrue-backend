package com.whiletruebackend.global.error.errorCode;

import com.whiletruebackend.global.error.dto.ErrorReason;

public interface BaseErrorCode {

    ErrorReason getErrorReason();
}