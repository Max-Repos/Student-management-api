package com.example.ProjectAPI.repository;

import com.example.ProjectAPI.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Long> {
}
