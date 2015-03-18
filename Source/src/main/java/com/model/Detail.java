package com.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by root on 17.03.15.
 */
@Entity
public class Detail {
    private int id;
    private int detailTypeId;
    private int price;
    private String quality;
    private int power;
    private String name;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "detail_type_id")
    public int getDetailTypeId() {
        return detailTypeId;
    }

    public void setDetailTypeId(int detailTypeId) {
        this.detailTypeId = detailTypeId;
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
    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
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

        if (detailTypeId != detail.detailTypeId) return false;
        if (id != detail.id) return false;
        if (power != detail.power) return false;
        if (price != detail.price) return false;
        if (name != null ? !name.equals(detail.name) : detail.name != null) return false;
        if (quality != null ? !quality.equals(detail.quality) : detail.quality != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + detailTypeId;
        result = 31 * result + price;
        result = 31 * result + (quality != null ? quality.hashCode() : 0);
        result = 31 * result + power;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
