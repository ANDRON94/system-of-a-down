package com.service.algorithms.schedule;

import com.model.Detail;
import com.model.Order;

import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */
public interface FindOperationCriteria {
    public List<Detail> find( Order order );
}
