package com.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name="computer")
public class Computer {
    private int id;
    private float price;
    private float quality;
    private float power;
    private List<Detail> detailList;

    @ManyToMany(cascade ={ CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name="computer_detail",
            joinColumns={@JoinColumn(name="computer_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="detail_id", referencedColumnName="id")})
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
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Column(name = "quality")
    public float getQuality() {
        return quality;
    }

    public void setQuality(float quality) {
        this.quality = quality;
    }


    @Column(name = "power")
    public float getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        return true;
    }

}
