package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andron94 on 06.05.15.
 */
public class Operation {

    protected List<Detail> getOperations( Order order ){
        List<Detail> operations = new ArrayList<Detail>();
        if( order.getStatus().getName().equals("NEW_ORDER") ){
            for( int i = 0; i < order.getCountComputers(); ++i ){
                List<Detail> temp = new ArrayList<Detail>(order.getComputer().getDetailList());
                operations.addAll(temp);
            }
        } else{
            for( Contract contract : order.getContractList() ){
                operations.add(contract.getDetail());
            }
        }
        return operations;
    }

}
