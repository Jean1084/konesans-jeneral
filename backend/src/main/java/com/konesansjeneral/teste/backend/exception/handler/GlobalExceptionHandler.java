package com.konesansjeneral.teste.backend.exception.handler;

import lombok.extern.slf4j.Slf4j;
import com.konesansjeneral.teste.backend.exception.BadRequestException;
import com.konesansjeneral.teste.backend.exception.NotFoundException;
import com.konesansjeneral.teste.backend.exception.RefreshTokenException;
import com.konesansjeneral.teste.backend.model.response.ErrorApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorApiResponse> handleException(BadRequestException e) {
        return handleException(e, BAD_REQUEST);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorApiResponse> handleException(NotFoundException e) {
        return handleException(e, NOT_FOUND);
    }

    @ExceptionHandler(value = RefreshTokenException.class)
    public ResponseEntity<ErrorApiResponse> handleException(RefreshTokenException e) {
        return handleException(e, UNAUTHORIZED);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorApiResponse> handleException(Exception e) {
        return handleException(e, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorApiResponse> handleException(Exception e, HttpStatus httpStatus) {
        log.error(e.getMessage(), e);
        var body = new ErrorApiResponse();
        body.setCode(httpStatus.value());
        body.setMessage(e.getMessage());
        return new ResponseEntity<>(body, httpStatus);
    }
}
