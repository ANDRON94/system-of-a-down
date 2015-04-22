package com.service;
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
    private DetailRepository detailRepository;

    private static final int detailTypeCount = Unit.detailTypeCount;

    private static final int evolutionSteps = 10000;

    private List<List<Detail>> db = new ArrayList<List<Detail>>(detailTypeCount);
    private Evolution evolution;

    public List<Detail> makeChoice(OrderDTO orderDTO){
        System.out.println("Choice service begin work!");
        init();
        if(evolution.createPopulation() != false){
            System.out.println("Alpha before evolution\n" + evolution.getPopulation().getAlpha().toString());
            evolution.makeStep(evolutionSteps);
            System.out.println("Alpha after evolution\n" + evolution.getPopulation().getAlpha().toString());
            return evolution.getPopulation().getAlpha().getDetails();
        } else {
            return null;
        }
    }

    private void init(){
        for (int i = 1; i <= detailTypeCount; i++ ){
            db.add(i - 1, detailRepository.findByDetailTypeId(i));
        }
        evolution = new Evolution(8000, 4.0f, 4.0f, db);
    }






}
