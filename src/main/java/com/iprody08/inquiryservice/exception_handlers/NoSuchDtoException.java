package com.iprody08.inquiryservice.exception_handlers;

public class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException(final String message) {
        super(message);
    }
}
