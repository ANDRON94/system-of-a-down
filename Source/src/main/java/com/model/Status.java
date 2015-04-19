package com.model;

import javax.persistence.*;

/**
 * Created by root on 19.04.15.
 */
@Entity
@Table(name = "status")
public class Status {
    private int id;
    private String name;


    @Id
    @Column(name = "idstatus",nullable = false,unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
