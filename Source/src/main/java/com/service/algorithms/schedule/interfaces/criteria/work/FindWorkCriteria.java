package com.service.algorithms.schedule.interfaces.criteria.work;

import com.model.Order;

import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */
public interface FindWorkCriteria {
    public boolean isWorkExist();
    public Order next();
}
