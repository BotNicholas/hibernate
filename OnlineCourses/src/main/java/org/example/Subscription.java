package org.example;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionId id;
    @Column(name = "subscription_date")
    private Date subscriptionDate;


    public Subscription(){}

    public Subscription(SubscriptionId id, Date subscriptionDate){
        this.id = id;
        this.subscriptionDate = subscriptionDate;
    }



    public Course getCourse() {
        return id.getCourse();
    }

    public void setCourse(Course course) {
        this.id.setCourse(course);
    }

    public Student getStudent() {
        return id.getStudent();
    }

    public void setStudent(Student student) {
        this.id.setStudent(student);
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
