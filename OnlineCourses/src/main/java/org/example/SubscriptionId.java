package org.example;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class SubscriptionId implements Serializable {

    public SubscriptionId(){

    }

    public SubscriptionId(Student student, Course course){
        this.course = course;
        this.student = student;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;
//    @Column(name = "student_id")
//    private int studentId;
//    @Column(name = "course_id")
//    private int courseId;

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
    public boolean equals(Object obj) {
        if( (((SubscriptionId)obj).getCourse().getId() == course.getId()) && (((SubscriptionId)obj).getStudent().getId() == student.getId()))
            return true;
        return false;
    }
}
