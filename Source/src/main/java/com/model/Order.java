package com.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name = "order_data")
public class Order {
    private int id;
    private Date deadilne;
    private int price;
    private Computer computer;
    private int countComputers;
    private String propouse;
    private Status status;
    private User user;


    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "count_computers")
    public int getCountComputers() {
        return countComputers;
    }

    public void setCountComputers(int countComputers) {
        this.countComputers = countComputers;
    }

    @Column(name = "propouse")
    public String getPropouse() {
        return propouse;
    }

    public void setPropouse(String propouse) {
        this.propouse = propouse;
    }



    @Basic
    @Column(name = "deadilne")
    public Date getDeadilne() {
        return deadilne;
    }

    public void setDeadilne(Date deadilne) {
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

    @ManyToOne(cascade ={ CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinColumn(name = "status_idstatus",referencedColumnName = "idstatus")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    @ManyToOne(cascade ={ CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_iduser",referencedColumnName = "iduser")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
