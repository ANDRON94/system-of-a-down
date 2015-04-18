package com.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name="computer")
public class Computer {
    private int id;
    private int price;
    private List<Detail> detailList;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_type_id",referencedColumnName = "id")
    public List<Detail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<Detail> detailList) {
        this.detailList = detailList;
    }

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
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        if (id != computer.id) return false;
        if (price != computer.price) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        return result;
    }
}
