package com.integration.user;

import com.controller.user.DTO.OrderDTO;
import com.model.Computer;
import com.model.Contract;
import com.model.Detail;
import com.model.Order;
import com.repository.ContractRepository;
import com.repository.OrderRepository;
import com.repository.StatusRepository;
import com.service.ChoiceService;
import com.service.evolution.Unit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by root on 18.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class OrderTest extends Assert{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ChoiceService choiceService;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    private ContractRepository contractRepository;
    private OrderDTO orderDTO;
    Date date=null;
    @Before
    public void initOrderDTO(){
         orderDTO= new OrderDTO();
        orderDTO.setCount(3);
        orderDTO.setCpuCount(4);
        try {
            orderDTO.setDeadilne(new GregorianCalendar(2016, Calendar.AUGUST, 5).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        orderDTO.setGpuCount(4);
        orderDTO.setHddCount(4);
        orderDTO.setMbCount(4);
        orderDTO.setRamCount(4);
        orderDTO.setCpuCount(4);
        orderDTO.setPower(4);
        orderDTO.setPrice(40000);
        orderDTO.setQuality(4);
    }
    @Test
    public void succesResearhOfDetailsToOrder(){
        Unit unit= choiceService.makeChoice(orderDTO);
        assertNotEquals(unit,null);
    }
    @Test
    public void  testPedingOrdersQuery(){
        System.out.println("\n\n\n\n\n Padding Test");
      List<Order> ordersList=orderRepository.findAllOrdersForPlane("IN_QUEUE");
        for(Order order : ordersList){
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
            for(Contract contract:order.getContractList()){
                System.out.println("ID:\t"+contract.getId());
                System.out.println("Detail_id:\t"+contract.getDetail().getId());
                System.out.println("Detail_name:\t"+contract.getDetail().getName());
                System.out.println("START:\t"+contract.getStart_date());
                System.out.println();
                System.out.println();
            }
        }
    }
    @Test
    public void  testProcisedOrders(){
        try {
            date= new SimpleDateFormat("yyyy/MM/dd").parse("2015/05/22");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Order> orders=orderRepository.findByStartAfterAndOrderStatusName("IN_PROSESS");
        System.out.println("\n\n\n\n Process Order with contects after some date");
        for(Order order : orders){
            System.out.println("OrderID:\t" +order.getId());
            for(Contract contract:contractRepository.getContractsAfterDateByIdOrder(date, order.getId())){
                System.out.println("ID:\t"+contract.getId());
                System.out.println("Detail_id:\t"+contract.getDetail().getId());
                System.out.println("Detail_name:\t"+contract.getDetail().getName());
                System.out.println("START:\t"+contract.getStart_date());
                System.out.println("PARAM_DATE:\t"+date);
                System.out.println();
                System.out.println();
            }
        }
    }

    @Test
    public void testMustDeclineOrders(){
        System.out.println("Order Must Decline test");
        for(Order order:orderRepository.findAllThatMustDecline(date)){
            System.out.println("ORDER_ID:\t"+order.getId());
            System.out.println("PARAM_DATE:\t"+date);
        }

    }
}
