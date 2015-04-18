package com.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name = "order")
public class Order {
    private int id;
    private Timestamp deadilne;
    private int price;
    private Computer computer;


    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    @OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "computer_id",referencedColumnName = "id")
    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (price != order.price) return false;
        if (deadilne != null ? !deadilne.equals(order.deadilne) : order.deadilne != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (deadilne != null ? deadilne.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }
}
