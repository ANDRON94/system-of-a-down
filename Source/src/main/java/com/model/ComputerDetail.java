package com.model;

import javax.persistence.*;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name = "computer_detail", schema = "", catalog = "calendar")
public class ComputerDetail {
    private int detailId;
    private int computerId;
    private int countDetails;
    private int id;

    @Basic
    @Column(name = "detail_id")
    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
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
    @Column(name = "count_details")
    public int getCountDetails() {
        return countDetails;
    }

    public void setCountDetails(int countDetails) {
        this.countDetails = countDetails;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerDetail that = (ComputerDetail) o;

        if (computerId != that.computerId) return false;
        if (countDetails != that.countDetails) return false;
        if (detailId != that.detailId) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = detailId;
        result = 31 * result + computerId;
        result = 31 * result + countDetails;
        result = 31 * result + id;
        return result;
    }
}
