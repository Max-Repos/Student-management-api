package com.example.ProjectAPI;

import com.example.ProjectAPI.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectAPIAdvice {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<String> handleNotFoundCourse(CourseNotFoundException courseNotFoundException){
        return new ResponseEntity<>("Course is not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CourseOutOfBound.class)
    public ResponseEntity<String> handleCourseOutOfBound(CourseOutOfBound courseOutOfBound){
        return new ResponseEntity<>("Student can not add more than two courses", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleNotFoundStudent(StudentNotFoundException studentNotFoundException){
        return new ResponseEntity<>("Student is not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TutorOutOfBound.class)
    public ResponseEntity<String> handleStudentOutOfBound(TutorOutOfBound tutorOutOfBound){
        return new ResponseEntity<>("Student can not add more than three tutor", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TutorNotFoundException.class)
    public ResponseEntity<String> handleNotFoundTutor(TutorNotFoundException tutorNotFoundException){
        return new ResponseEntity<>("Tutor is not found", HttpStatus.NOT_FOUND);
    }
}
