package com.whiletruebackend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseWrapper<T> {

    private int httpStatus;
    private String message;
    private T data;
}