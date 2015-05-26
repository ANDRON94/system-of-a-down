package com.unit.algorithm.comparator;

import com.model.Detail;
import com.model.Order;
import com.repository.DetailTypeRepository;
import com.service.algorithms.schedule.interfaces.criteria.operation.impl.DurationOperationComparator;
import com.service.algorithms.schedule.interfaces.criteria.work.impl.DeadlineWorkComparator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created by max on 25.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class OperationComparatorTesting extends Assert {
    @Autowired
    private DetailTypeRepository detailTypeRepository;

    private Detail detail;

    @Before
    public void initData(){
        detail = new Detail();
        detail.setDetailType(detailTypeRepository.findOne(1));
    }
    @Test
    public void successCompare(){
        Comparator<Detail> durationOperationComparator =new <Detail>DurationOperationComparator();
        Detail testOrder=new Detail();

        testOrder.setDetailType(detailTypeRepository.findOne(2));
        int result =  durationOperationComparator.compare(detail, testOrder);
        assertEquals(1,result);

        testOrder.setDetailType(detailTypeRepository.findOne(1));
        result=durationOperationComparator.compare(detail, testOrder);
        assertEquals(0, result);

    }
}
