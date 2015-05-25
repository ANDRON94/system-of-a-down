package com.service;
import com.model.Computer;
import com.model.Order;
import com.model.Status;
import com.repository.OrderRepository;
import com.repository.StatusRepository;
import com.repository.UserRepository;
import com.service.evolution.Evolution;
import com.service.evolution.Unit;
import com.controller.user.DTO.OrderDTO;
import com.model.Detail;
import com.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusRepository statusRepository;

    private static final int detailTypeCount = Unit.detailTypeCount;

    private static final int evolutionSteps = 10000;

    private List<List<Detail>> db = new ArrayList<List<Detail>>(detailTypeCount);
    private Evolution evolution;




    public Unit makeChoice(OrderDTO orderDTO){
        System.out.println("Choice service begin work!");
        init(orderDTO);
        if(evolution.createPopulation() != false){
            System.out.println("Alpha before evolution\n" + evolution.getPopulation().getAlpha().toString());
            evolution.makeStep(evolutionSteps);
            System.out.println("Alpha after evolution\n" + evolution.getPopulation().getAlpha().toString());
            return evolution.getPopulation().getAlpha();
        } else {
            return null;
        }

    }



    private void init(OrderDTO orderDTO){
        for (int i = 1; i <= detailTypeCount; i++ ){
            db.add(i - 1, detailRepository.findByDetailTypeId(i));
        }
        int [] a = {orderDTO.getCpuCount(),orderDTO.getGpuCount(),orderDTO.getMbCount(),orderDTO.getRamCount(),orderDTO.getHddCount()};
        evolution = new Evolution(orderDTO.getPrice(), orderDTO.getPower(), orderDTO.getQuality(), db, a);
    }



    public List<Order> findOrdersOfClient(String email){
        return orderRepository.findByUser_Email(email);
    }
    public Order findOrderByIdAndEmail(int id,String email){
        return orderRepository.findOneByIdAndUser_Email(id, email);
    }

    public boolean createAndSaveOrderData(OrderDTO orderDTO) {
        Unit unit =makeChoice(orderDTO);
        if (unit == null) {
            System.out.println("Computer not found!");
            return false;
        } else {
            Order order = new Order();
            Computer computer = new Computer();

            int produceTime = 0;
            List<Detail> allDetails = new ArrayList<>();
            Detail currentDetail = unit.getDetails().get(0);
            produceTime += orderDTO.getCpuCount() * currentDetail.getDetailType().getProduceTime();
            for (int i = 0; i < orderDTO.getCpuCount(); i++) {
                allDetails.add(currentDetail.clone());
            }
            currentDetail = unit.getDetails().get(1);
            produceTime += orderDTO.getGpuCount() * currentDetail.getDetailType().getProduceTime();
            for (int i = 0; i < orderDTO.getGpuCount(); i++) {
                allDetails.add(currentDetail.clone());
            }
            currentDetail = unit.getDetails().get(2);
            produceTime += orderDTO.getMbCount() * currentDetail.getDetailType().getProduceTime();
            for (int i = 0; i < orderDTO.getMbCount(); i++) {
                allDetails.add(currentDetail.clone());
            }
            currentDetail = unit.getDetails().get(3);
            produceTime += orderDTO.getRamCount() * currentDetail.getDetailType().getProduceTime();
            for (int i = 0; i < orderDTO.getRamCount(); i++) {
                allDetails.add(currentDetail.clone());
            }
            currentDetail = unit.getDetails().get(4);
            produceTime += orderDTO.getHddCount() * currentDetail.getDetailType().getProduceTime();
            for (int i = 0; i < orderDTO.getHddCount(); i++) {
                allDetails.add(currentDetail.clone());
            }
            computer.setDetailList(allDetails);
            order.setPerformance_time(produceTime * orderDTO.getCount());
            computer.setQuality(unit.getAverageQuality());
            computer.setPower(unit.getAveragePower());
            computer.setPrice(unit.getTotalPrise());
            order.setComputer(computer);
            order.setContractList(null);
            order.setDeadilne(orderDTO.getDeadilne());
            order.setPropouse(null);
            order.setCountComputers(1);
            order.setPrice(unit.getTotalPrise() * orderDTO.getCount());
            order.setCountComputers(orderDTO.getCount());
            order.setStatus(statusRepository.findOne(1));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            order.setUser(userRepository.findOneByEmail(auth.getName()));
            orderRepository.save(order);
            System.out.println("Computer found! Works starts!");
            return true;
        }
    }
}
