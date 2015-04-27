package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
        Entry<Worker, Integer> lessBusyWorker = null;
        for ( Entry<Worker, Integer> worker : busyFactor.entrySet() ){
            if( ( lessBusyWorker == null ||
                    lessBusyWorker.getValue() > worker.getValue() ) &&
                 worker.getKey().getSpecializations().contains(operation.getDetailType())){

                lessBusyWorker = worker;
            }
        }
        //update busy factor
        if( lessBusyWorker != null ){
            Integer newBusyFactor = lessBusyWorker.getValue() +
                    operation.getDetailType().getProduceTime();
            busyFactor.put( lessBusyWorker.getKey(), newBusyFactor);
            return lessBusyWorker.getKey();
        } else{
            return null;
        }
    }
}
