package com.iprody08.inquiryservice.exception_handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(NotFoundException exception) {
        final ErrorDto data = new ErrorDto();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(Exception exception) {
        final ErrorDto data = new ErrorDto();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
