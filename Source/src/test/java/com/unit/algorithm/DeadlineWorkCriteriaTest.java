package com.unit.algorithm;

import com.model.Order;
import com.repository.OrderRepository;
import com.service.algorithms.schedule.DeadlineWorkCriteria;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by andron94 on 27.04.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class DeadlineWorkCriteriaTest extends Assert {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testDeadlineCriteria(){
        List<Order> orders = orderRepository.findAllOrdersForPlane("IN_QUEUE");
        System.out.println("Orders: " + orders.size());
        DeadlineWorkCriteria deadlineWorkCriteria = new DeadlineWorkCriteria(orders);

        while (deadlineWorkCriteria.isWorkExist()){
            Order order = deadlineWorkCriteria.next();
            System.out.println("Order: "+order.getId() );
            System.out.println("Deadline: " + order.getDeadilne());
        }
        assertTrue(orders.isEmpty());
    }
}
