package com.service;

import com.controller.OrderDTO;
import com.model.Detail;
import com.model.Order;
import com.repository.DetailRepository;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mantixop on 4/21/15.
 */
@Service
public class ChoiceService {

    private final int ORDERS_PER_PAGE = 10;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DetailRepository detailRepository;
    private int detailTypeCount = 5;
    private int populationSize = 5;
    private List<List<Detail>> db = new ArrayList<List<Detail>>(detailTypeCount);

    public List<Detail> makeChoice(OrderDTO orderDTO){
        System.out.println("Choice service begin work!");
        createPopulation();
        return null;
    }

    private void createPopulation(){
        for (int i = 1; i <= detailTypeCount; i++ ){
            db.add(detailRepository.findByDetailTypeId(i));
        }
        System.out.print(db.get(1).size());
        System.out.print(db.get(2).size());
    }

    class Unit {
        private List<Detail> details;
        private int totalPrise;
        private float averageQuality;
        private float averagePower;
        private boolean full;

        Unit() {
            totalPrise = 0;
            averagePower = 0;
            averageQuality = 0;
            details = new ArrayList<Detail>(detailTypeCount);
            full = false;
        }

        private void recount(){
            int sumQuality = 0;
            int sumPower = 0;
            totalPrise = 0;
            for(int i = 0; i <details.size(); i++ ){
                sumPower += details.get(i).getPower();
                sumQuality += details.get(i).getQuality();
                totalPrise += details.get(i).getPrice();
            }
            averageQuality = sumQuality / details.size();
            averagePower = sumPower / details.size();
            if (details.size() == detailTypeCount){
                System.out.println("Detail full");
                full = true;
            } else {
                full = false;
            }
        }

        public void setDetail(Detail detail){
            details.set(detail.getDetailType().getId() - 1, detail);
            recount();
        }

        public List<Detail> getDetails() {
            return details;
        }

        public int getTotalPrise() {
            return totalPrise;
        }

        public float getAverageQuality() {
            return averageQuality;
        }

        public float getAveragePower() {
            return averagePower;
        }

        public boolean isFull() {
            return full;
        }
    }

    public List<Order> findOrdersOfClient(String email){
       return orderRepository.findByUser_Email(email);
    }
    public Order findOrderByIdAndEmail(int id,String email){
        return orderRepository.findOneByIdAndUser_Email(id,email);
    }
}
