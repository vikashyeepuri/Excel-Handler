package com.example.excelHandler.exception;

import com.example.excelHandler.response.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Clock clock;

    public GlobalExceptionHandler(Clock clock) {
        this.clock = clock;
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponseDto> handleException(ExternalApiException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        LocalDateTime.now(clock),
                        HttpStatus.BAD_GATEWAY.value(),
                        "EXTERNAL API EXCEPTION",
                        request.getRequestURI(),
                        ex.getMessage()
                ), HttpStatus.BAD_GATEWAY
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequest(IllegalArgumentException ex, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        LocalDateTime.now(clock),
                        HttpStatus.BAD_REQUEST.value(),
                        "INVALID INPUT",
                        request.getRequestURI(),
                        ex.getMessage()
                ), HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntime(RuntimeException ex,  HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        LocalDateTime.now(clock),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "INTERNAL SERVER ERROR",
                        request.getRequestURI(),
                        ex.getMessage()
                ), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
