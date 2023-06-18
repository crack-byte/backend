package com.tripshare.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    public static <T> ResponseEntity<Response<T>> generateResponse(T data, String message, String status, HttpStatus httpStatus) {
        Response<T> response = new Response<>();
        response.setStatus(status);
        response.setMessage(message);
        response.setData(data);
        return response.entity(httpStatus);
    }

}
