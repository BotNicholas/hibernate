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

    public int getCourseId() {
        return id.getCourseId();
    }

    public void setCourseId(int id) {
        this.id.setCourseId(id);
    }

    public int getStudentId() {
        return id.getStudentId();
    }

    public void setStudentId(int id) {
        this.id.setStudentId(id);
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
