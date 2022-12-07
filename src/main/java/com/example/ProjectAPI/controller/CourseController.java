package com.example.ProjectAPI.controller;

import com.example.ProjectAPI.entity.Course;
import com.example.ProjectAPI.entity.Student;
import com.example.ProjectAPI.entity.Tutor;
import com.example.ProjectAPI.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseService.getCourses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id){
        return new ResponseEntity<>(courseService.getCourse(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Course> saveCourse(@RequestBody Course Course){
        return new ResponseEntity<>(courseService.saveCourse(Course), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{course_id}/students")
    public ResponseEntity<List<Student>> getStudentEnrolledInCourse(@PathVariable Long course_id){
        return new ResponseEntity<>(courseService.getStudentByCourse(course_id),HttpStatus.OK);
    }

    @GetMapping("{course_id}/tutors")
    public ResponseEntity<List<Tutor>> getTutorsByCourseId(@PathVariable Long course_id){
        return new ResponseEntity<>(courseService.getTutorsByCourseId(course_id),HttpStatus.OK);
    }
}
