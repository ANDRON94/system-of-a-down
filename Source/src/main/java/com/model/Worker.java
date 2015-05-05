package com.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name = "worker")
public class Worker {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private String sename;
    private List<DetailType> specializations;
    @NotNull
    private int cash;


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sename")
    public String getSename() {
        return sename;
    }

    public void setSename(String sename) {
        this.sename = sename;
    }
    @Basic
    @Column(name = "cash")
    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="specialization",
            joinColumns={@JoinColumn(name="worker_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="detail_type_id", referencedColumnName="id")})
    public List<DetailType> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<DetailType> specializations) {
        this.specializations = specializations;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (id != worker.id) return false;
        if (name != null ? !name.equals(worker.name) : worker.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
