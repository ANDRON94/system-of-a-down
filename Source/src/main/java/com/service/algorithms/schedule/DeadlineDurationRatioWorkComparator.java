package com.service.algorithms.schedule;

import com.model.Detail;
import com.model.Order;

import java.util.Comparator;

/**
 * Created by andron94 on 27.04.15.
 */
public class DeadlineDurationRatioWorkComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        double o1Ratio = (double)o1.getDeadilne().getTime() / getOrderDuration(o1);//TODO: get order duration
        double o2Ratio = (double)o2.getDeadilne().getTime() / getOrderDuration(o2);
        return Double.compare(o1Ratio,o2Ratio);
    }

    private int getOrderDuration( Order order ){
        int orderDuration = 0;
        for(Detail detail : order.getComputer().getDetailList() ){
            orderDuration += detail.getDetailType().getProduceTime();
        }
        orderDuration *= order.getCountComputers();
        return orderDuration;
    }
}
