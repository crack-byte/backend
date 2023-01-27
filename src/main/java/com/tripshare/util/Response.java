package com.tripshare.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Setter
@JsonInclude(NON_EMPTY)
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
