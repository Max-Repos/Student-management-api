package com.example.ProjectAPI.exception;

public class CourseOutOfBound extends Throwable {


        public CourseOutOfBound(Long id) {
            super("The course id '" + id + "' can not be added");
        }

}
