package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by root on 17.03.15.
 */
@Entity
@IdClass(ContractPK.class)
public class Contract {
    private int workerId;
    private int orderId;
    private int computerDetailId;

    @Id
    @Column(name = "worker_id")
    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "computer_detail_id")
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

        Contract contract = (Contract) o;

        if (computerDetailId != contract.computerDetailId) return false;
        if (orderId != contract.orderId) return false;
        if (workerId != contract.workerId) return false;

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
