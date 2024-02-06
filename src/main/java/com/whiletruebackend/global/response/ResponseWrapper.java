package com.whiletruebackend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ResponseWrapper<T> {

    private HttpStatus httpStatus;
    private String message;
    private T data;
}