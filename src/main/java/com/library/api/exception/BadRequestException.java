package com.library.api.exception;

/**
 * Custom exception for bad request scenarios
 * Returns HTTP 400 status code
 */
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }
}
