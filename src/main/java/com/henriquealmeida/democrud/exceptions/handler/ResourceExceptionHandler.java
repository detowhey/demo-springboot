package com.henriquealmeida.democrud.exceptions.handler;

import com.henriquealmeida.democrud.exceptions.DataBaseException;
import com.henriquealmeida.democrud.exceptions.ResourceNotFoundException;
import com.henriquealmeida.democrud.exceptions.error.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return createErrorResponse("Resource not found", HttpStatus.NOT_FOUND, e, request);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> dataBase(DataBaseException e, HttpServletRequest request) {
        return createErrorResponse("Database error", HttpStatus.BAD_REQUEST, e, request);
    }

    private ResponseEntity<StandardError> createErrorResponse(String error, HttpStatus httpStatus, Exception e, HttpServletRequest request) {
        StandardError standardError = new StandardError(Instant.now(), httpStatus.value(), error, e.getMessage(), request.getRequestURI());
        log.error(error, e);
        return ResponseEntity.status(httpStatus).body(standardError);
    }
}
