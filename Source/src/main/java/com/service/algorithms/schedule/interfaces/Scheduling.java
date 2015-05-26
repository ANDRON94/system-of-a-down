package com.service.algorithms.schedule.interfaces;

import com.model.Contract;
import com.service.algorithms.schedule.interfaces.criteria.operation.FindOperationCriteria;
import com.service.algorithms.schedule.interfaces.criteria.work.FindWorkCriteria;
import com.service.algorithms.schedule.interfaces.criteria.worker.FindWorkerCriteria;

import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */
public interface Scheduling {
    public List<Contract> makeSchedule( FindWorkCriteria findWorks,
                                        FindOperationCriteria findOperations,
                                        FindWorkerCriteria findWorkers );
}
