package org.example;

import jakarta.persistence.*;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {
    @EmbeddedId
    LinkedPurchaseListId id;

    public LinkedPurchaseList(){}

    public LinkedPurchaseList(LinkedPurchaseListId id){
        this.id = id;
    }

    public LinkedPurchaseListId getId() {
        return id;
    }

    public void setId(LinkedPurchaseListId id) {
        this.id = id;
    }
}
