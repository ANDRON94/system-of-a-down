package com.service.algorithms.schedule.interfaces.criteria.work.impl;

import com.model.Order;

import java.util.Comparator;

/**
 * Created by andron94 on 27.04.15.
 */
public class DeadlineWorkComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getDeadilne().compareTo(o2.getDeadilne());
    }
}
