package com.dao;

import com.model.Computer;
import com.model.Contract;
import com.model.Detail;
import com.model.Order;
import com.repository.ContractRepository;
import com.repository.OrderRepository;
import org.junit.Before;
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
 * Created by root on 18.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class OrderTest {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ContractRepository contractRepository;
    private Date date=null;
    @Before
    public void initDate(){
        try {
            date= new SimpleDateFormat("yyyy-mm-dd").parse("2012-05-20 14:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void  testPedingOrders(){


      List<Order> ordersList=orderRepository.findAllOrdersForPlane("IN_QUEUE");
       /*   for (Order order:ordersList){
            System.out.println("ID:\t"+order.getId());
            System.out.println("StATUS:\t"+order.getStatus().getName());
            System.out.println("CONTRACTS:");
            for (Contract contract:order.getContractList()) {
                System.out.println("contract_ID:\t" + contract.getId());
                System.out.println("detail_Name:\t"+contract.getDetail().getName());
            }
            System.out.println();
        }*/

       /* List<Order> orderList= orderRepository.findAll();
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
        }*/
    }
    @Test
    public void  testProcisedOrders(){
        try {
            date= new SimpleDateFormat("yyyy-mm-dd").parse("2012-05-20 14:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Contract> contracts=contractRepository.findByStartAfterAndOrderStatusName("IN_PROSESS",date);
        for(Contract contract : contracts){
            System.out.println("ID:\t"+contract.getId());
            System.out.println("Detail_id:\t"+contract.getDetail().getId());
            System.out.println("Detail_name:\t"+contract.getDetail().getName());
            System.out.println("START:\t"+contract.getStart_date());
            System.out.println("ORDER:\t"+contract.getOrder().getId());
            for(Contract contract1:contract.getOrder().getContractList()){
                System.out.println(contract1.getId());
            }
            System.out.println();
            System.out.println();
        }

    }

}
