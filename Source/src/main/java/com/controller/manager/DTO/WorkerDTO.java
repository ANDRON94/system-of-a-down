package com.controller.manager.DTO;

import java.util.List;

/**
 * Created by root on 18.04.15.
 */
public class WorkerDTO {
    private String name;
    private  String sename;
    private int cash;
    private List<Integer> specializations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSename() {
        return sename;
    }

    public void setSename(String sename) {
        this.sename = sename;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public List<Integer> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Integer> specializations) {
        this.specializations = specializations;
    }
}
