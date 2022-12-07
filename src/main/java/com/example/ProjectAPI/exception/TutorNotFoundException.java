package com.example.ProjectAPI.exception;

public class TutorNotFoundException extends RuntimeException {

    public TutorNotFoundException(Long id) {
        super("The tutor id '" + id + "' does not exist in our records");
    }

}