package com.metadata.schoolregistrationsystem.student.entity;

import com.metadata.schoolregistrationsystem.common.entity.AbstractVersionedAuditableEntity;
import com.metadata.schoolregistrationsystem.course.entity.Course;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "STUDENT")
public class Student extends AbstractVersionedAuditableEntity {

    private static final int MAX_COURSE_CAPACITY = 5;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STUDENT_COURSE",
            joinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID", referencedColumnName = "ID"))
    private Set<Course> courses;

    public void update(Student student) {
        this.name = student.getName();
        this.surname = student.getSurname();
    }

    public boolean hasEnoughCourseCapacity(int numOfCourses) {
        return courses.size() + numOfCourses <= MAX_COURSE_CAPACITY;
    }

    public void addNewCourses(List<Course> newCourses) {
        courses.addAll(newCourses);
    }
}