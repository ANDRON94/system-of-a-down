package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andron94 on 22.04.15.
 */
public class BusyWorkerCriteria implements  FindWorkerCriteria {
    //TODO: fix busyFactor. and Criteria

    private Map<Worker,Integer> busyFactor = new HashMap<Worker, Integer>();

    BusyWorkerCriteria( List<Worker> freeWorkers, List<Contract> startContracts ){
        for( Worker worker : freeWorkers ){
            //at start point all workers has zero busy factor
            busyFactor.put(worker,0);
        }
    }

    public Worker find( Detail operation ) {
        return null;
    }
}