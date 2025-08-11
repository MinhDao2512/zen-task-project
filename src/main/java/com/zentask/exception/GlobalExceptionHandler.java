package com.zentask.exception;

import com.zentask.config.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<ApiResponse<Object>> handleAuthenticationException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ApiResponse.error("AUTHENTICATION_ERROR", ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ApiResponse.ValidationError> validationErrorList = ex.getBindingResult().getFieldErrors().stream().map(
                fieldError -> ApiResponse.ValidationError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build()
        ).toList();

        return new ResponseEntity<>(ApiResponse.error("FIELD_INVALID", "Dữ liệu đầu vào không hợp lệ.", validationErrorList), HttpStatus.BAD_REQUEST);
    }
}
