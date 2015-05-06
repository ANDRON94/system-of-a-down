package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andron94 on 06.05.15.
 */
public class CheapWorkerCriteria implements FindWorkerCriteria {

    private Map<Worker,Integer> busyFactor = new HashMap<Worker, Integer>();

    public CheapWorkerCriteria( List<Worker> freeWorkers, List<Contract> startContracts ){
        for( Worker worker : freeWorkers ){
            //at start point all workers has zero busy factor
            busyFactor.put(worker,0);
        }
    }

    public void restart(){
        for( Map.Entry<Worker,Integer> busyFactorEntry : busyFactor.entrySet() ){
            //at start point all workers has zero busy factor
            busyFactorEntry.setValue(0);
        }
    }
//TODO: REFACTOR!!!
    public Worker find( Detail operation ) {
        Map.Entry<Worker, Integer> lessCheapBusyWorker = null;
        int type = 1;
        // 1 - means find by [max -> t/w]
        // 0 - means find by [min -> w]
        for ( Map.Entry<Worker, Integer> worker : busyFactor.entrySet() ){
            if( worker.getValue() == 0 ){
                type = 0;
                break;
            }
        }
        if( type == 0  ){
            for ( Map.Entry<Worker, Integer> worker : busyFactor.entrySet() ) {
                if ((lessCheapBusyWorker == null ||
                        (lessCheapBusyWorker.getKey().getCash() > worker.getKey().getCash() && worker.getValue() == 0 )) &&
                        worker.getKey().getSpecializations().contains(operation.getDetailType())) {

                    lessCheapBusyWorker = worker;
                }
            }
        } else if( type == 1 ){
            for ( Map.Entry<Worker, Integer> worker : busyFactor.entrySet() ) {
                double workerFactor = (double)worker.getValue() / worker.getKey().getCash();
                if ((lessCheapBusyWorker == null ||
                     ((double)lessCheapBusyWorker.getValue()/lessCheapBusyWorker.getKey().getCash()) < workerFactor) &&
                        worker.getKey().getSpecializations().contains(operation.getDetailType())) {

                    lessCheapBusyWorker = worker;
                }
            }
        }
        //update busy factor
        if( lessCheapBusyWorker != null ){
            Integer newBusyFactor = lessCheapBusyWorker.getValue() +
                    operation.getDetailType().getProduceTime();
            busyFactor.put( lessCheapBusyWorker.getKey(), newBusyFactor);
            return lessCheapBusyWorker.getKey();
        } else{
            return null;
        }
    }

}
