package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Order;
import com.repository.ContractRepository;
import com.repository.DetailRepository;
import com.repository.OrderRepository;
import com.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andron94 on 20.04.15.
 */

@Service
public class ScheduleService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DetailRepository detailRepository;

    List<List<Contract>> schedule(){
        List<List<Contract>> schedules = new ArrayList<>();
        List<Order> orders /*= orderRepository.findAllOrdersForPlane()*/;
        //AmazingScheduling scheduler = new AmazingScheduling();
        //DeadlineWorkCriteria deadlineWorkCriteria = new DeadlineWorkCriteria(orders);


        return  schedules;
    }
}
