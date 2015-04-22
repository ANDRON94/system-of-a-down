package com.service;
import com.model.Order;
import com.repository.OrderRepository;
import com.service.evolution.Evolution;
import com.service.evolution.Unit;
import com.controller.OrderDTO;
import com.model.Detail;
import com.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mantixop on 4/21/15.
 */
@Service
public class ChoiceService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DetailRepository detailRepository;

    private static final int detailTypeCount = 5;
    private List<List<Detail>> db = new ArrayList<List<Detail>>(detailTypeCount);
    private Evolution evolution;



    public List<Detail> makeChoice(OrderDTO orderDTO){
        System.out.println("Choice service begin work!");
        init();
        if(evolution.createPopulation() != false){
            System.out.println(evolution.getAlpha().toString());
            return evolution.getAlpha().getDetails();
        } else {
            return null;
        }

    }



    private void init(){
        for (int i = 1; i <= detailTypeCount; i++ ){
            db.add(i - 1, detailRepository.findByDetailTypeId(i));
        }
        int []A = {1,1,1,1,1};
        evolution = new Evolution(3000, 3.0f, 3.0f, A, db);        //price , power , qa , array
    }



    public List<Order> findOrdersOfClient(String email){
        return orderRepository.findByUser_Email(email);
    }
    public Order findOrderByIdAndEmail(int id,String email){
        return orderRepository.findOneByIdAndUser_Email(id, email);
    }
}
