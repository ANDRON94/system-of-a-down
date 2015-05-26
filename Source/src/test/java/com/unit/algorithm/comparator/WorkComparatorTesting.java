package com.unit.algorithm.comparator;

import com.model.Order;
import com.service.algorithms.schedule.interfaces.criteria.work.impl.DeadlineWorkComparator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Created by max on 25.05.15.
 */

public class WorkComparatorTesting extends Assert {

    private Order order;
    public int compare(Order o1, Order o2) {
        return o1.getDeadilne().compareTo(o2.getDeadilne());
    }
    @Before
    public void initData(){
        order = new Order();
        order.setDeadilne(new GregorianCalendar(2015, Calendar.JANUARY,3).getTime());
    }
    @Test
    public void successCompare(){
        Comparator<Order> deadlineWorkComparator =new <Order> DeadlineWorkComparator();
        Order testOrder=new Order();

        testOrder.setDeadilne(new GregorianCalendar(2014, Calendar.JANUARY,3).getTime());
        int result =  deadlineWorkComparator.compare(order, testOrder);
        assertEquals(1,result);

        testOrder.setDeadilne(new GregorianCalendar(2016, Calendar.JANUARY,3).getTime());
        result=deadlineWorkComparator.compare(order, testOrder);
        assertEquals(-1,result);

        testOrder.setDeadilne(new GregorianCalendar(2015, Calendar.JANUARY,3).getTime());
        result=deadlineWorkComparator.compare(order,testOrder);
        assertEquals(0,result);
    }
}
