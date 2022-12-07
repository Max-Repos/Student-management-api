package com.example.ProjectAPI.exception;

public class TutorOutOfBound extends RuntimeException{
    public TutorOutOfBound(Long id) {
        super("The tutor id '" + id + "' can not be added");
    }
}
