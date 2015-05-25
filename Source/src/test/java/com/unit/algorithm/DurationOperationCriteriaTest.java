package com.unit.algorithm;

import com.model.Detail;
import com.model.Order;
import com.repository.ContractRepository;
import com.repository.OrderRepository;
import com.service.algorithms.schedule.DurationOperationCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by andron94 on 27.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class DurationOperationCriteriaTest {


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ContractRepository contractRepository;

    @Test
    public void testDurationOperationCriteria(){
        Date date = null;
        try {
            date= new SimpleDateFormat("yyyy-mm-dd").parse("2012-05-9 14:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Order> orders = orderRepository.findByStatus_Name("NEW_ORDER");
        System.out.println("Orders: " + orders.size());
        for( Order order : orders) {
            System.out.println("Order Id:" + order.getId());
            DurationOperationCriteria durationOperationCriteria = new DurationOperationCriteria();
            List<Detail> details = durationOperationCriteria.find(order);
            for (Detail detail : details) {
                System.out.println("Detail: " + detail.getId());
                System.out.println("Detail name : " + detail.getName());
                System.out.println("Prdoduce time: " + detail.getDetailType().getProduceTime());
            }
        }

    }
}
