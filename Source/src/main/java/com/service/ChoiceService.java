package com.service;

import com.controller.OrderDTO;
import com.model.Detail;
import com.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mantixop on 4/21/15.
 */
@Service
public class ChoiceService {
    @Autowired
    private DetailRepository detailRepository;

    public List<Detail> makeChoice(OrderDTO orderDTO){
        System.out.println("Choice service begin work!");
        return null;
    }


}
