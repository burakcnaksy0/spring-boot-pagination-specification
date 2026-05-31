package com.burakcanaksoy.springbootpaginationspecification.advanced;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<E> {

    private HttpStatus httpStatus;
    private int status;
    private boolean success;
    private String message;
    private E data;
    private String path;
    private Map<String, String> validationErrors;
    
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ApiResponse<T> success(HttpStatus httpStatus) {
        return ApiResponse.<T>builder()
                .success(true)
                .httpStatus(httpStatus)
                .status(httpStatus.value())
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data, HttpStatus httpStatus) {
        return ApiResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .httpStatus(httpStatus)
                .status(httpStatus.value())
                .build();
    }

    public static <T> ApiResponse<T> error(String message, HttpStatus httpStatus, String path) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .httpStatus(httpStatus)
                .status(httpStatus.value())
                .path(path)
                .build();
    }

    public static <T> ApiResponse<T> validationError(String message, Map<String, String> validationErrors, String path) {
        return ApiResponse.<T>builder()
                .success(false)
                .message(message)
                .validationErrors(validationErrors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .status(HttpStatus.BAD_REQUEST.value())
                .path(path)
                .build();
    }

}
