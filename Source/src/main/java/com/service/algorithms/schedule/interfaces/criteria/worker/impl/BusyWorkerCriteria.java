package com.service.algorithms.schedule.interfaces.criteria.worker.impl;

import com.model.Contract;
import com.model.Detail;
import com.model.Worker;
import com.service.algorithms.schedule.interfaces.criteria.worker.FindWorkerCriteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by andron94 on 22.04.15.
 */
public class BusyWorkerCriteria implements FindWorkerCriteria {

    private Map<Worker,Integer> busyFactor = new HashMap<Worker, Integer>();

    public BusyWorkerCriteria( List<Worker> freeWorkers, List<Contract> startContracts ){
        for( Worker worker : freeWorkers ){
            //at start point all workers has zero busy factor
            busyFactor.put(worker,0);
        }
    }

    public void restart(){
        for( Entry<Worker,Integer> busyFactorEntry : busyFactor.entrySet() ){
            //at start point all workers has zero busy factor
            busyFactorEntry.setValue(0);
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
