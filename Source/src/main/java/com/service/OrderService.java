package com.service;

import com.model.Order;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by root on 18.04.15.
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private final int ORDERS_PER_PAGE = 10;

    @Transactional
    public Page<Order> getAllOrders( Integer pageNumber ){
        Page<Order> orders = orderRepository.findAll(
                new PageRequest(pageNumber - 1, ORDERS_PER_PAGE, Sort.Direction.DESC,"id")
        );
        return orders;
    }

    @Transactional
    public Order getOrderById( Integer orderId ){
        Order order = orderRepository.findOne(orderId);
        return order;
    }
}
