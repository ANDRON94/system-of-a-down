package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Order;
import com.model.Worker;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by andron94 on 20.04.15.
 */
public class AmazingScheduling  implements Scheduling{

    private Date start;
    private Map<Worker,Date> nextStartDate = new HashMap<Worker,Date>();

    public AmazingScheduling( Date start, List<Worker> freeWorkers){
        this.start = start;
        for( Worker worker : freeWorkers ){
            //all workers start to execute operations
            //from the beginning
            nextStartDate.put(worker,start);
        }
    }

    public List<Contract> makeSchedule(FindWorkCriteria findWorks,
                                       FindOperationCriteria findOperations,
                                       FindWorkerCriteria findWorkers,
                                       FindOptimizeCashCriteria c) {
        List<Contract> temporaryContracts = new ArrayList<Contract>();
        while(findWorks.isWorkExist()){
            Order order = findWorks.next();
            List<Detail> operations = findOperations.find(order);
            for( Detail operation : operations ){
                Worker worker = findWorkers.find(operation);
                //create contract
                Contract newContract = new Contract();
                newContract.setOrder(order);
                newContract.setDetail(operation);
                newContract.setWorker(worker);
                Date interval[] =  findPeriodOfWork(worker, operation);
                newContract.setStart_date(interval[0]);
                newContract.setEnd_date(interval[1]);

                temporaryContracts.add(newContract);
            }
        }
        return temporaryContracts;
    }

    private Date[] findPeriodOfWork(Worker worker, Detail operation){
        Date interval[] = new Date[2];
        interval[0] = nextStartDate.get(worker);
        interval[1] = new Date( interval[0].getTime() +
                TimeUnit.MINUTES.toMillis(operation.getDetailType().getProduceTime()));
        if(false/*check*/){
            //findPeriodOfWork new start date
            //findPeriodOfWork new finish date
        }
        nextStartDate.put(worker,interval[1]);
        return interval;
    }


}
