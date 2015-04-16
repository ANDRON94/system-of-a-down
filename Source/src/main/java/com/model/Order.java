package com.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by root on 17.03.15.
 */
@Entity
public class Order {
    private int id;
    private int computerId;
    private Timestamp deadilne;
    private int price;
    private int userIduser;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "computer_id")
    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    @Basic
    @Column(name = "deadilne")
    public Timestamp getDeadilne() {
        return deadilne;
    }

    public void setDeadilne(Timestamp deadilne) {
        this.deadilne = deadilne;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "user_iduser")
    public int getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(int userIduser) {
        this.userIduser = userIduser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (computerId != order.computerId) return false;
        if (id != order.id) return false;
        if (price != order.price) return false;
        if (userIduser != order.userIduser) return false;
        if (deadilne != null ? !deadilne.equals(order.deadilne) : order.deadilne != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + computerId;
        result = 31 * result + (deadilne != null ? deadilne.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + userIduser;
        return result;
    }
}
