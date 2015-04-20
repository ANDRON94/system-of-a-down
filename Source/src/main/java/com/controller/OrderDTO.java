package com.controller;

import java.sql.Timestamp;

/**
 * Created by Mantixop on 4/20/15.
 */
public class OrderDTO {
    private int price;
    private int power;
    private int quality;

    private int cpuCount;
    private int gpuCount;
    private int mbCount;
    private int ramCount;
    private int hddCount;

    private Timestamp deadilne;

    public OrderDTO() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public int getGpuCount() {
        return gpuCount;
    }

    public void setGpuCount(int gpuCount) {
        this.gpuCount = gpuCount;
    }

    public int getMbCount() {
        return mbCount;
    }

    public void setMbCount(int mbCount) {
        this.mbCount = mbCount;
    }

    public int getRamCount() {
        return ramCount;
    }

    public void setRamCount(int ramCount) {
        this.ramCount = ramCount;
    }

    public Timestamp getDeadilne() {
        return deadilne;
    }

    public void setDeadilne(Timestamp deadilne) {
        this.deadilne = deadilne;
    }
}
