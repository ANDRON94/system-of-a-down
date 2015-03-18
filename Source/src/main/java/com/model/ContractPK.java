package com.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by root on 17.03.15.
 */
public class ContractPK implements Serializable {
    private int workerId;
    private int orderId;
    private int computerDetailId;

    @Column(name = "worker_id")
    @Id
    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    @Column(name = "order_id")
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "computer_detail_id")
    @Id
    public int getComputerDetailId() {
        return computerDetailId;
    }

    public void setComputerDetailId(int computerDetailId) {
        this.computerDetailId = computerDetailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContractPK that = (ContractPK) o;

        if (computerDetailId != that.computerDetailId) return false;
        if (orderId != that.orderId) return false;
        if (workerId != that.workerId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = workerId;
        result = 31 * result + orderId;
        result = 31 * result + computerDetailId;
        return result;
    }
}
