package com.example.ProjectAPI.service;

import com.example.ProjectAPI.entity.Course;
import com.example.ProjectAPI.entity.Student;
import com.example.ProjectAPI.entity.Tutor;
import com.example.ProjectAPI.exception.TutorNotFoundException;
import com.example.ProjectAPI.repository.CourseRepository;
import com.example.ProjectAPI.repository.StudentRepository;
import com.example.ProjectAPI.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    StudentRepository studentRepository;
    static Course unwrapCourse(Optional<Course> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new TutorNotFoundException(id);
    }

    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
    public Course getCourse(Long id){
        Optional<Course> course = courseRepository.findById(id);
        return unwrapCourse(course,id);
    }

    public Course saveCourse(Course course){
        return courseRepository.save(course);
    }

    public List<Student> getStudentByCourse(Long course_id) {
        List<Student> temp = (List<Student>) studentRepository.findAll();
        List<Student> ans = new ArrayList<>();
//        System.out.println(temp);
//        System.out.println(temp.size());
        for (int i = 1; i <= temp.size(); i++) {
            Student student = studentRepository.findById((long) i).get();
//            System.out.println("-------stud---------"+student.toString().toString());
            Set<Course> temp_course = student.getCourses();
//            System.out.println("----setCourse------"+temp_course);
            for (Course val:temp_course){
//                System.out.println("---val--"+val.getId());
                if(val.getId() == course_id) {
//                    System.out.println("---studId---" + student.getId());
                    ans.add(student);
                }
            }

        }
        return ans;
    }

    public List<Tutor> getTutorsByCourseId(Long course_id) {
        List<Student> tutors = (List<Student>) studentRepository.findAll();
        List<Tutor> ans = new ArrayList<>();
        for (int i = 1; i <= tutors.size(); i++) {
            Tutor tutor = tutorRepository.findById((long) i).get();
            Set<Course> courses = tutor.getCourses();
            for (Course course:courses){
                if (course.getId() == course_id){
                    ans.add(tutor);
                }
            }
        }
        return ans;
    }
}
