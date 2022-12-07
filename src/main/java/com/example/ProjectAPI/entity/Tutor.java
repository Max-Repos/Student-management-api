package com.example.ProjectAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @NonNull
    @Column(name = "experience")
    private String experience;
   
    @ManyToMany
    @JoinTable(
            name = "tutor_courses",
            joinColumns = @JoinColumn(name = "tutor_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id")
    )
    private Set<Course> courses;

    @JsonIgnore
    @ManyToMany(mappedBy = "tutors")
    private Set<Student> students;
}
