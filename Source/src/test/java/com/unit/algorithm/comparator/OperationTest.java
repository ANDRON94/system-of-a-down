package com.unit.algorithm.comparator;

import com.model.Computer;
import com.model.Detail;
import com.model.DetailType;
import com.model.Order;
import com.repository.DetailRepository;
import com.repository.OrderRepository;
import com.repository.StatusRepository;
import com.service.algorithms.schedule.interfaces.criteria.operation.impl.util.Operation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by max on 25.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class OperationTest extends Assert{
    @Autowired
    private DetailRepository orderRepository;
    @Autowired
    private StatusRepository statusRepository;

    private Order data;
    @Before
    public void initData(){
        data=new Order();
        data.setPrice(10000);
        data.setDeadilne(new GregorianCalendar(2015, Calendar.JANUARY,20).getTime());
        Computer computer=new Computer();
        computer.setPower(4);
        computer.setPrice(4);
        computer.setQuality(4);
        List<Detail> details= new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            details.add(orderRepository.findOne(i));
        }
        computer.setDetailList(details);
        data.setContractList(null);
        data.setStatus(statusRepository.findOneByName("NEW_ORDER"));
    }
    @Test
    public void testOperation(){
        Operation operation = new Operation();
        assertNotNull(operation.getOperations(data));
        data.setStatus(statusRepository.findOneByName("IN_PROSESS"));
    }
}
