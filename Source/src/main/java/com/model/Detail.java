
package com.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name = "detail")
public class Detail{
    private int id;
    private DetailType detailType;
    @NotNull
    private int price;
    @NotNull
    private int quality;
    @NotNull
    private int power;
    @NotNull
    private String name;

    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(cascade ={ CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="detail_type_id",referencedColumnName ="id")
    public DetailType getDetailType() {
        return detailType;
    }

    public void setDetailType(DetailType detailType) {
        this.detailType = detailType;
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
    @Column(name = "quality")
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    @Basic
    @Column(name = "power")
    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Detail detail = (Detail) o;


        if (id != detail.id) return false;
        if (power != detail.power) return false;
        if (price != detail.price) return false;
        if (name != null ? !name.equals(detail.name) : detail.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + power;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return  "Name: " + getName() + " Quality:" + getPower()
                + " Power: " + getPower() + " Prise: " + getPrice();
    }


    public Detail clone() {

        Detail detail1= new Detail();
        detail1.setId(this.id);
        detail1.setName(this.name);
        detail1.setPrice(this.price);
        detail1.setPower(this.power);
        detail1.setQuality(this.quality);
        detail1.setDetailType(this.detailType);
        return detail1;
    }
}
