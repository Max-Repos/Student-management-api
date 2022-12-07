package com.example.ProjectAPI.controller;

import com.example.ProjectAPI.PDFGenerator;
import com.example.ProjectAPI.entity.Student;
import com.example.ProjectAPI.exception.CourseOutOfBound;
import com.example.ProjectAPI.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudents(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<Student> studentList = studentService.getAllStudents();

        PDFGenerator generator = new PDFGenerator();
        generator.setStudentList(studentList);
        generator.generate(response);
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id){
        return new ResponseEntity<>(studentService.getStudent(id),HttpStatus.OK);
    }

    @PutMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<Student> enrollCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) throws CourseOutOfBound {
        return new ResponseEntity<>(studentService.addCourseToStudent(studentId,courseId), HttpStatus.OK);
    }

    @PutMapping("/{studentId}/tutor/{tutorId}")
    public ResponseEntity<Student> enrollTutorToStudent(@PathVariable Long studentId,@PathVariable Long tutorId){
        return new ResponseEntity<>(studentService.addTutorToStudent(studentId,tutorId),HttpStatus.OK);
    }
    @PutMapping("/{studentId}/tutor/{tutorId}/course/{courseId}")
    public ResponseEntity<Student> enrollCourseToTutorInStudent(@PathVariable Long studentId,@PathVariable Long tutorId,@PathVariable Long courseId){
        return new ResponseEntity<>(studentService.enrollCourseToTutorInStudent(studentId,tutorId,courseId),HttpStatus.OK);
    }


}
