package com.service.algorithms.schedule;

import com.model.Contract;

import java.util.List;

/**
 * Created by andron94 on 30.04.15.
 */
public class TotalSalaryOptimizeCashCriteria implements FindOptimizeCashCriteria {
    @Override
    public List<Contract> findOptimizeSchedule( List<List<Contract>> schedules ) {
        double minSalary = Double.MAX_VALUE;
        List<Contract> optimizeSchedule = null;
        for( List<Contract> schedule : schedules ){
            double currSalary = calcScheduleTotalSalary(schedule);
            if( minSalary > currSalary ){
                minSalary = currSalary;
                optimizeSchedule = schedule;
            }
        }
        return optimizeSchedule;
    }

    public int findOptimizeScheduleByCash( List<Double> cashes ){
        double minSalary = Double.MAX_VALUE;
        int optimizeSchedule = -1;
        for ( int i = 0; i < cashes.size(); ++i ){
            if( minSalary > cashes.get(i) ){
                minSalary = cashes.get(i);
                optimizeSchedule = i;
            }
        }
        return optimizeSchedule;
    }

    public double calcScheduleTotalSalary( List<Contract> schedule ){
        final int MILLI_TO_HOURS = 1000 * 60 * 60;
        double totalSalary = 0;
        for( Contract contract : schedule ){
            double workTime = (contract.getEnd_date().getTime() -
                contract.getStart_date().getTime() ) / MILLI_TO_HOURS;
            totalSalary += workTime * contract.getWorker().getCash();
        }
        return totalSalary;
    }
}
