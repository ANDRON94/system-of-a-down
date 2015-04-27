package com.service.algorithms.schedule;

import com.model.Order;

import java.util.*;

/**
 * Created by andron94 on 21.04.15.
 */
public class DeadlineWorkCriteria implements FindWorkCriteria {

    private List<Order> works = new ArrayList<Order>();

    public DeadlineWorkCriteria( List<Order> works ){
        this.works = works;

        Collections.sort(this.works, new DeadlineWorkComparator());
    }

    public boolean isWorkExist() {
        return !works.isEmpty();
    }

    public Order next() {
        return works.remove(0);
    }
}
