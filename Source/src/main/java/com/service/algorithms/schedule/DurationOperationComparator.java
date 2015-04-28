package com.service.algorithms.schedule;

import com.model.Detail;

import java.util.Comparator;

/**
 * Created by andron94 on 27.04.15.
 */
public class DurationOperationComparator implements Comparator<Detail> {
    @Override
    public int compare(Detail o1, Detail o2) {
        return Integer.compare( o1.getDetailType().getProduceTime(),
               o2.getDetailType().getProduceTime() );
    }
}
