package com.controller.user.DTO;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Mantixop on 4/20/15.
 */
public class OrderDTO {
    @NotNull @Min(0)
    private int price;
    private int power;
    private int quality;

    private int cpuCount;
    private int gpuCount;
    private int mbCount;
    private int ramCount;
    private int hddCount;
    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull
    private Date deadilne;


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

    public Date getDeadilne() {
        return deadilne;
    }

    public void setDeadilne(Date deadilne) {
        this.deadilne = deadilne;
    }

    public int getHddCount() {
        return hddCount;
    }

    public void setHddCount(int hddCount) {
        this.hddCount = hddCount;
    }
}
