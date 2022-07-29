package com.metadata.schoolregistrationsystem.course.entity;

import com.metadata.schoolregistrationsystem.common.entity.AbstractVersionedAuditableEntity;
import com.metadata.schoolregistrationsystem.student.entity.Student;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "COURSE")
public class Course extends AbstractVersionedAuditableEntity {

    private static final int MAX_STUDENT_CAPACITY = 50;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private Set<Student> students;

    public void update(Course course) {
        this.name = course.getName();
    }

    public boolean hasEnoughStudentCapacity() {
        return students.size() < MAX_STUDENT_CAPACITY;
    }
}
