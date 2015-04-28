package com.service.algorithms.schedule;

import com.model.Contract;

import java.util.Date;
import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */
public interface Scheduling {
    public List<Contract> makeSchedule( FindWorkCriteria findWorks,
                                        FindOperationCriteria findOperations,
                                        FindWorkerCriteria findWorkers );
}
