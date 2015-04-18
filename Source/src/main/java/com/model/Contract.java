package com.model;

import javax.persistence.*;

/**
 * Created by root on 17.03.15.
 */
@Entity
@Table(name = "contract")
public class Contract {
    private int id;
    private Worker worker;
    private Order order;
    private Detail detail;


    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne(cascade ={ CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_id",referencedColumnName = "id")
    public Worker getWorker() {
        return worker;
    }
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }



    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_id",referencedColumnName = "id")
    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

}
