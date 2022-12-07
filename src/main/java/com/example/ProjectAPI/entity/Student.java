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
@Table(name = "student",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tutor_id","course_id"})
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "name")
    public String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private Course course;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tutor_id",referencedColumnName = "id")
    private Tutor tutor;

    @ManyToMany
    @JoinTable(
            name = "student_tutor",
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tutor_id",referencedColumnName = "id")
    )
    public Set<Tutor> tutors;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", tutor=" + tutor +
                ", tutors=" + tutors +
                ", courses=" + courses +
                '}';
    }

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id")
    )
    public Set<Course> courses;
}
