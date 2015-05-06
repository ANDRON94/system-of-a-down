package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Order;

import java.util.ArrayList;
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
