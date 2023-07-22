package org.example;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LinkedPurchaseListId implements Serializable {
//    @Column(name = "student_id")
//    private int studentId;
//    @Column(name = "course_id")
//    private int courseId;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", columnDefinition = "int unsigned")
    Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", columnDefinition = "int unsigned")
    Course course;

    public LinkedPurchaseListId(){

    }

    public LinkedPurchaseListId(Student student, Course course){
        this.student = student;
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedPurchaseListId that = (LinkedPurchaseListId) o;
//        return Objects.equals(student, that.student) && Objects.equals(course, that.course);
        if(student.getId() == that.getStudent().getId() && course.getId() == that.getCourse().getId())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}
