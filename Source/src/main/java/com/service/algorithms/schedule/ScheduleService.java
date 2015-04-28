package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Order;
import com.model.Worker;
import com.repository.ContractRepository;
import com.repository.DetailRepository;
import com.repository.OrderRepository;
import com.repository.WorkerRepository;
import com.util.DateTimeFormatter;
import com.util.TodayManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public List<List<Contract>> schedule( Order newOrder ){
        List<List<Contract>> schedules = new ArrayList<>();
        Date startDate = DateTimeFormatter.parseStringToDate(TodayManipulator.readToday());
        List<Worker> freeWorkers = workerRepository.findAll();
        List<Order> pendingOrders = orderRepository.findAllOrdersForPlane("IN_QUEUE");
        List<Order> processingOrders = orderRepository.findByStartAfterAndOrderStatusName("IN_PROSESS", startDate);
        List<Order> works = new ArrayList<>();
        works.addAll(pendingOrders);
        works.addAll(processingOrders);
        works.add(newOrder);
        AmazingScheduling scheduler = new AmazingScheduling(startDate,freeWorkers);
        //first algorithm
        DeadlineWorkCriteria deadlineWorkCriteria = new DeadlineWorkCriteria(works);
        DurationOperationCriteria durationOperationCriteria = new DurationOperationCriteria();
        BusyWorkerCriteria busyWorkerCriteria = new BusyWorkerCriteria(freeWorkers, null);

        schedules.add( scheduler.makeSchedule(
                deadlineWorkCriteria,
                durationOperationCriteria,
                busyWorkerCriteria
        ));
        //second algorithm
        //bla bla bla

        return  schedules;
    }

    public Order validateSchedule( List<Contract> schedule ){
        return  new Order();
        //TODO: validate schedule
    }
}
