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
public class DurationOperationCriteria implements FindOperationCriteria {

    public List<Detail> find(Order order) {
        List<Detail> operations = new ArrayList<Detail>();
        if( order.getStatus().getName().equals("NEW_ORDER") ){
            for( int i = 0; i < order.getCountComputers(); ++i ){
                //TODO: check whether identical lists
                List<Detail> temp = new ArrayList<Detail>(order.getComputer().getDetailList());
                operations.addAll(temp);
            }
        } else{
            //TODO: get list from contracts
            for( Contract contract : order.getContractList() ){
                operations.add(contract.getDetail());
            }
        }
        Collections.sort(operations,new DurationOperationComparator().reversed());
        return operations;
    }
}
