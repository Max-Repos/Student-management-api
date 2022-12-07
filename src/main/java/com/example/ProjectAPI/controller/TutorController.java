package com.example.ProjectAPI.controller;

import com.example.ProjectAPI.entity.Course;
import com.example.ProjectAPI.entity.Student;
import com.example.ProjectAPI.entity.Tutor;
import com.example.ProjectAPI.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tutor")
public class TutorController {

    @Autowired
    TutorService tutorService;
    @GetMapping("/all")
    public ResponseEntity<List<Tutor>> getTutors(){
        return new ResponseEntity<>(tutorService.getTutors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tutor> getTutor(@PathVariable Long id){
        return new ResponseEntity<>(tutorService.getTutor(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tutor> saveTutor(@RequestBody Tutor tutor){
        return new ResponseEntity<>(tutorService.saveTutor(tutor), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutor(@PathVariable Long id){
        tutorService.deleteTutor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("{tutor_id}/course/{course_id}")
    public ResponseEntity<Tutor> addCourseToTutor(@PathVariable Long tutor_id, @PathVariable Long course_id){
        return new ResponseEntity<>(tutorService.addCourseToTutor(tutor_id,course_id),HttpStatus.OK);
    }

    @GetMapping("{tutor_id}/students")
    public ResponseEntity<List<Student>> getStudentByTutorId(@PathVariable Long tutor_id){
        return new ResponseEntity<>(tutorService.getStudentByTutorId(tutor_id),HttpStatus.OK);
    }

}
