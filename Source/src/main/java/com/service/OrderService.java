package com.service;

import com.model.Order;
import com.model.Status;
import com.model.Worker;
import com.repository.OrderRepository;
import com.repository.StatusRepository;
import com.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 18.04.15.
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private WorkerRepository workerRepository;
    private final int ORDERS_PER_PAGE = 10;

    @Transactional
    public Page<Order> getAllOrders( Integer pageNumber ){
        Page<Order> orders = orderRepository.findAll(
                new PageRequest(pageNumber - 1, ORDERS_PER_PAGE, Sort.Direction.DESC,"id")
        );
        return orders;
    }

    public List<Worker> getAllWorkers(){
       return workerRepository.findAll();
    }
    public Status findStatusByName(String statusName){
        return statusRepository.findOneByName(statusName);
    }
    @Transactional
    public Order getOrderById( Integer orderId ){
        Order order = orderRepository.findOne(orderId);
        return order;
    }
    public void backToTheFuture(Date date){


        List<Order> ordersForProcessing= orderRepository.findAllThatMustProcessing(date);

        System.out.println("Orders for processing on date "+date);
        for(Order order:ordersForProcessing){
            System.out.println("Order:\t" + order.getId());
            order.setStatus(statusRepository.findOne(3));
            orderRepository.save(order);
        }
        List<Order> ordersForDecline=orderRepository.findAllThatMustDecline(date);

        System.out.println("Orders for decline on date "+date);
        for(Order order:ordersForDecline){
            System.out.println("Order:\t"+order.getId());
            order.setStatus(statusRepository.findOne(5));
            orderRepository.save(order);
        }


        List<Order> ordersForDone=orderRepository.findAllThatMustDone(date);
        System.out.println("Orders for done on date "+date);
        for(Order order:ordersForDone){
            order.setStatus(statusRepository.findOne(4));
            System.out.println("Order:\t"+order.getId());
            orderRepository.save(order);
        }
    }
    public void saveOrder(Order order){
        orderRepository.save(order);
    }
}
