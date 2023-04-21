package com.avg_dev.grocery_list.webutils;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    @Builder
    @Data
    private static class GenericResponse<T> {

        private HttpStatus status;
        private String message;
        private T data;

        @Override
        public String toString() {
            return "{" +
                    "status=" + status +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static <T> ResponseEntity getResponse(HttpStatus status, String msg, T body) {
        return ResponseEntity
                .status(status)
                .body(GenericResponse.builder()
                        .status(status)
                        .message(msg)
                        .data(body)
                        .build()
                );
    }

}
