package com.example.ProjectAPI.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

//    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", duration='" + duration + '\'' +
//                ", students=" + students +
//                ", student=" + student +
//                ", tutors=" + tutors +
//                '}';
//    }

    @NonNull
    @Column(name = "duration")
    private String duration;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    @JsonIgnore
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Student> student;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<Tutor> tutors;
}
