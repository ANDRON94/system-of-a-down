package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
import com.model.Worker;

import java.util.Date;
import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */
public interface FindWorkerCriteria {
    public Worker find( Detail operation );
}
