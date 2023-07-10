package org.example;

import java.io.Serializable;
import jakarta.persistence.*;

@Embeddable
public class PurchaseListId implements Serializable {
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;


    public PurchaseListId(){

    }

    public PurchaseListId(String studentName, String courseName){
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object obj) {
        if((((PurchaseListId)obj).getCourseName() == courseName) && (((PurchaseListId)obj).getStudentName() == studentName))
            return true;
        return false;
    }
}
