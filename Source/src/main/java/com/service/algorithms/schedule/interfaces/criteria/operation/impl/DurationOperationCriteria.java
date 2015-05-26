package com.service.algorithms.schedule.interfaces.criteria.operation.impl;

import com.model.Detail;
import com.model.Order;
import com.service.algorithms.schedule.interfaces.criteria.operation.FindOperationCriteria;
import com.service.algorithms.schedule.interfaces.criteria.operation.impl.util.Operation;

import java.util.Collections;
import java.util.List;

/**
 * Created by andron94 on 22.04.15.
 */
public class DurationOperationCriteria extends Operation implements FindOperationCriteria {

    public List<Detail> find(Order order) {
        List<Detail> operations = getOperations(order);
        Collections.sort(operations,Collections.reverseOrder(new DurationOperationComparator()));
        return operations;
    }
}
