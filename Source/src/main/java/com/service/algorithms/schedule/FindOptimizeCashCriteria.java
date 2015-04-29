package com.service.algorithms.schedule;

import com.model.Contract;

import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */
public interface FindOptimizeCashCriteria {
    public List<Contract> findOptimizeSchedule( List<List<Contract>> schedules );
}
