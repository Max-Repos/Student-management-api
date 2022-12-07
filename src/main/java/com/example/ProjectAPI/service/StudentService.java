package com.example.ProjectAPI.service;

import com.example.ProjectAPI.entity.Course;
import com.example.ProjectAPI.entity.Student;
import com.example.ProjectAPI.entity.Tutor;
import com.example.ProjectAPI.exception.CourseOutOfBound;
import com.example.ProjectAPI.exception.StudentNotFoundException;
import com.example.ProjectAPI.exception.TutorOutOfBound;
import com.example.ProjectAPI.repository.CourseRepository;
import com.example.ProjectAPI.repository.StudentRepository;
import com.example.ProjectAPI.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TutorRepository tutorRepository;

    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return unwrapStudent(student,id);
    }

    public Student addCourseToStudent(Long studentId, Long courseId) throws CourseOutOfBound {
        Student student = getStudent(studentId);
        Optional<Course> course = courseRepository.findById(courseId);
        Course unwrappedCourse = CourseService.unwrapCourse(course,courseId);
        if (student.getCourses().size()<2) {
            student.getCourses().add(unwrappedCourse);
            return studentRepository.save(student);
        }else {
            throw new CourseOutOfBound(courseId);
        }
    }

    static Student unwrapStudent(Optional<Student> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new StudentNotFoundException(id);
    }

    public Student addTutorToStudent(Long studentId, Long tutorId) {
        Student student = getStudent(studentId);
        Optional<Tutor> Tutor = tutorRepository.findById(tutorId);
        Tutor unwrappedTutor = TutorService.unwrapTutor(Tutor,tutorId);
        if (student.getTutors().size()<3) {
            student.getTutors().add(unwrappedTutor);
            return studentRepository.save(student);
        }else {
            throw new TutorOutOfBound(tutorId);
        }
    }

    public Student enrollCourseToTutorInStudent(Long studentId, Long tutorId, Long courseId) {
        Student student = getStudent(studentId);
        Optional<Tutor> Tutor = tutorRepository.findById(tutorId);
        Tutor unwrappedTutor = TutorService.unwrapTutor(Tutor,tutorId);
        Course course= courseRepository.findById(courseId).get();
        unwrappedTutor.getCourses().add(course);
        return studentRepository.save(student);
    }


}
