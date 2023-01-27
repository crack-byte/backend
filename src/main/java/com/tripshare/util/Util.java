package com.tripshare.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static <T> ResponseEntity<Response<T>> generateResponse(T data, String message, String status, HttpStatus httpStatus) {
        Response<T> response = new Response<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response.entity(httpStatus);
    }

}
