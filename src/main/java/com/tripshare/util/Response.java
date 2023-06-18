package com.tripshare.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Setter
@JsonInclude(NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {

    private T data;
    private String status;
    private String errorCode;
    private String message;

    public ResponseEntity<Response<T>> entity(HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(this);
    }

    public ResponseEntity<Response<T>> entity() {
        return this.entity(HttpStatus.OK);
    }

}
