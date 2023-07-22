package org.example;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "PurchaseList")
public class PurchaseList {

    @EmbeddedId
    private PurchaseListId id;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;



    public PurchaseList(){}

    public PurchaseList(PurchaseListId id, int price, Date subscriptionDate){
        this.id = id;
        this.price = price;
        this.subscriptionDate = subscriptionDate;
    }



    public String getStudentName() {
        return id.getStudentName();
    }

    public void setStudentName(String name) {
        this.id.setStudentName(name);
    }

    public String getCourseName() {
        return id.getCourseName();
    }

    public void setCourseName(String name) {
        this.id.setCourseName(name);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
