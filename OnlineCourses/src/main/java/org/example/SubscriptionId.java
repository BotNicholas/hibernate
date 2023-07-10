package org.example;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class SubscriptionId implements Serializable {

    public SubscriptionId(){

    }

    public SubscriptionId(int courseId, int studentId){
        this.courseId = courseId;
        this.studentId = studentId;
    }

    //here can be used relationship annotation instead of these ides!!!
    @Column(name = "student_id")
    private int studentId;
    @Column(name = "course_id")
    private int courseId;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object obj) {
        if( (((SubscriptionId)obj).getCourseId() == courseId) && (((SubscriptionId)obj).getStudentId() == studentId))
            return true;
        return false;
    }
}
