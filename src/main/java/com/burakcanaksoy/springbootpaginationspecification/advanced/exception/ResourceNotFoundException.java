package com.burakcanaksoy.springbootpaginationspecification.advanced.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
