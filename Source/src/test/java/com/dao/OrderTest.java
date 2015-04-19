package com.dao;

import com.model.Computer;
import com.model.Detail;
import com.model.Order;
import com.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by root on 18.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class OrderTest {
    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void  testSelectingOrders(){
        List<Order> orderList= orderRepository.findAll();
        for(Order order : orderList){
            System.out.println(order.getDeadilne());
            Computer computer = order.getComputer();
            System.out.println(order.getUser().getEmail());
            System.out.println(computer.getPrice());
            for(Detail detail : computer.getDetailList()){
                System.out.println(detail.getName());
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }

}
