package com.service.algorithms.schedule;

import com.model.Order;

import java.util.*;

/**
 * Created by andron94 on 21.04.15.
 */
public class DeadlineWorkCriteria implements FindWorkCriteria {

    private List<Order> works = new ArrayList<Order>();

    DeadlineWorkCriteria( List<Order> works ){
        this.works = works;

        Collections.sort(this.works, new DeadlineWorkComparator());
        for( Order work : works ){
            System.out.println("Order: "+work.getId() );
            System.out.println("Deadline: " + work.getDeadilne());
        }
    }

    public boolean isWorkExist() {
        return !works.isEmpty();
    }

    public Order next() {
        return works.remove(0);
    }
}
