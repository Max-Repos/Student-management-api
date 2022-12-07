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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TutorService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    TutorRepository tutorRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    CourseService courseService;
    public static Tutor unwrapTutor(Optional<Tutor> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new TutorNotFoundException(id);
    }

    public List<Tutor> getTutors() {
        return (List<Tutor>) tutorRepository.findAll();
    }

    public Tutor getTutor(Long id) {
        return tutorRepository.findById(id).get();
    }

    public Tutor saveTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public void deleteTutor(Long id) {
        tutorRepository.deleteById(id);
    }

    public Tutor addCourseToTutor(Long tutor_id, Long course_id) {
        Tutor tutor = tutorRepository.findById(tutor_id).get();
        Course course = courseRepository.findById(course_id).get();
        tutor.getCourses().add(course);
        return tutorRepository.save(tutor);
    }

    public List<Student> getStudentByTutorId(Long tutor_id) {
        List<Student> temp = (List<Student>) studentRepository.findAll();
        List<Student> ans = new ArrayList<>();

        for (int i = 1; i <= temp.size(); i++) {
            Student student = studentRepository.findById((long) i).get();

            Set<Tutor> temp_tutor = student.getTutors();

            for (Tutor val:temp_tutor){

                if(val.getId() == tutor_id) {

                    ans.add(student);
                }
            }

        }
        return ans;
    }
}
