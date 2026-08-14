package com.api.grade_manager.exception;

public class SemesterNotFoundException extends RuntimeException {
    public SemesterNotFoundException(String message) {
        super(message);
    }
}
