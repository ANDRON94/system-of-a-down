package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Order;
import com.model.Worker;
import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */
public class AmazingScheduling  implements Scheduling{

    private Date start;
    private List<Worker> freeWorkers;

    public AmazingScheduling( Date start, List<Worker> freeWorkers){
        this.start = start;
        this.freeWorkers = freeWorkers;
    }
    //FUUUUUUUUUUUUUCK
    public List<Contract> makeSchedule(FindWorkCriteria findWorks,
                                       FindOperationCriteria findOperations,
                                       FindWorkerCriteria findWorkers,
                                       FindOptimizeCashCriteria c) {
        /*List<Contract> temporaryContracts = new ArrayList<Contract>();
        List<Order> orders = findWorks.find();
        for( Order order : orders ){
            List<Detail> details = findOperations.find(order);
            for( Detail detail : details ){
                Worker worker = findWorkers.find(temporaryContracts,freeWorkers);
                //create contract
                Contract some = new Contract();
                some.setWorker(worker);
                some.setDetail(detail);
                some.setOrder(order);

                temporaryContracts.add(some);
            }
        }
        wjhi
        //validation*/
        while(findWorks.isWorkExist()){
            Order order = findWorks.next();
            List<Detail> operations = findOperations.find(order);
            for( Detail operation : operations ){

            }
        }
        return null;
    }


}
