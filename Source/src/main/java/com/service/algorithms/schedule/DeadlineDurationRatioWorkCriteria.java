package com.service.algorithms.schedule;

import com.model.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 30.04.15.
 */
public class DeadlineDurationRatioWorkCriteria implements FindWorkCriteria {
    private List<Order> works = new ArrayList<Order>();

    public DeadlineDurationRatioWorkCriteria( List<Order> works ){
        this.works = works;

        Collections.sort(this.works, new DeadlineDurationRatioWorkComparator());
    }

    public boolean isWorkExist() {
        return !works.isEmpty();
    }

    public Order next() {
        return works.remove(0);
    }
}
