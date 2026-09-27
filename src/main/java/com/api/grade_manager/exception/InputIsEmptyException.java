package com.api.grade_manager.exception;

public class InputIsEmptyException extends RuntimeException {
    public InputIsEmptyException(String message) {
        super(message);
    }
}