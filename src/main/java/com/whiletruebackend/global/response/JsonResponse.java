package com.whiletruebackend.global.response;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonResponse {

    public static ResponseEntity<ResponseWrapper<Nullable>> ok(String message) {
        ResponseWrapper<Nullable> responseWrapper = new ResponseWrapper<>(HttpStatus.OK, message, null);
        return ResponseEntity.ok(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> ok(String message, T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(HttpStatus.OK, message, data);
        return ResponseEntity.ok(responseWrapper);
    }


    public static ResponseEntity<ResponseWrapper<Nullable>> of(HttpStatus httpStatus, String message) {
        ResponseWrapper<Nullable> responseWrapper = new ResponseWrapper<>(httpStatus, message, null);
        return ResponseEntity.status(httpStatus).body(responseWrapper);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> of(HttpStatus httpStatus, String message, T data) {
        ResponseWrapper<T> responseWrapper = new ResponseWrapper<>(httpStatus, message, data);
        return ResponseEntity.status(httpStatus).body(responseWrapper);
    }

}