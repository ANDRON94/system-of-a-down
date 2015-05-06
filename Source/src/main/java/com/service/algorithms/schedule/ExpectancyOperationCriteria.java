package com.service.algorithms.schedule;

import com.model.Detail;
import com.model.DetailType;
import com.model.Order;
import com.model.Worker;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by andron94 on 06.05.15.
 */
public class ExpectancyOperationCriteria extends Operation implements FindOperationCriteria {

    private Map<DetailType, Double> expectancyFactor = new HashMap<>();

    public ExpectancyOperationCriteria( List<Order> works, List<Worker> workers ){
        for ( Order work : works ){
            for( Detail operation : getOperations(work)  ){
                DetailType detailType = operation.getDetailType();
                Double factor = expectancyFactor.get(detailType);
                if( factor == null ) {
                    factor = 0.0d;
                }
                factor += detailType.getProduceTime();
                expectancyFactor.put(detailType,factor);
            }
        }
        Map<DetailType,Integer> workerCount = new HashMap<>();
        for ( Worker worker : workers ){
            for( DetailType detailType : worker.getSpecializations() ){
                Integer count = workerCount.get(detailType);
                if( count == null ){
                    count = 0;
                }
                count += 1;
                workerCount.put(detailType, count);
            }
        }
        for( Map.Entry<DetailType, Double> entry : expectancyFactor.entrySet() ){
            entry.setValue( entry.getValue().doubleValue() /
                    (double)workerCount.get(entry.getKey()).intValue() );
        }
    }


    @Override
    public List<Detail> find(Order order) {
        List<Detail> operations = getOperations(order);
        //sort by detail duration
        //redundant cause duration in the same class is the same
        /*Collections.sort(operations,
                Collections.reverseOrder(new DurationOperationComparator()));*/
        //sort by detail class
        Collections.sort(operations,
                Collections.reverseOrder(new ExpectancyOperationComparator(expectancyFactor)));
        return operations;
    }
}
