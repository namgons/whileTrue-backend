package com.whiletruebackend.global.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

public class JsonResponse {

    public static ResponseEntity<ResponseWrapper<Nullable>> ok(String message) {
        ResponseWrapper<Nullable> responseWrapper = new ResponseWrapper<>(HttpStatus.OK.value(), message, null);
        return ResponseEntity.ok(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> ok(String message, T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(HttpStatus.OK.value(), message, data);
        return ResponseEntity.ok(responseWrapper);
    }


    public static ResponseEntity<ResponseWrapper<Nullable>> of(HttpStatus httpStatus, String message) {
        ResponseWrapper<Nullable> responseWrapper = new ResponseWrapper<>(httpStatus.value(), message, null);
        return ResponseEntity.status(httpStatus).body(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> of(HttpStatus httpStatus, String message, T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(httpStatus.value(), message, data);
        return ResponseEntity.status(httpStatus).body(responseWrapper);
    }

}
