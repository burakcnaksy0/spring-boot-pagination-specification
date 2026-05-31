package com.burakcanaksoy.springbootpaginationspecification.advanced.exception;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
