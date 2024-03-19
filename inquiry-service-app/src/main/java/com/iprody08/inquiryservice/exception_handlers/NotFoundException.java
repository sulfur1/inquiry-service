package com.iprody08.inquiryservice.exception_handlers;

public class NotFoundException extends RuntimeException {

    public NotFoundException(final String message) {
        super(message);
    }
}
