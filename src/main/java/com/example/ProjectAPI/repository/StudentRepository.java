package com.example.ProjectAPI.repository;

import com.example.ProjectAPI.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student,Long> {

}
