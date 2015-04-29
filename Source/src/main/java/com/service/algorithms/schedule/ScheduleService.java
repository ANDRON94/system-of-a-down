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

import java.util.*;

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

    public List<List<Contract>> schedule( Order newOrder ,Date startDate){
        List<List<Contract>> schedules = new ArrayList<>();
        List<Worker> freeWorkers = workerRepository.findAll();
        List<Order> pendingOrders = orderRepository.findAllOrdersForPlane("IN_QUEUE");
        List<Order> processingOrders =orderRepository.findByStatus_Name("IN_PROSESS");
        for(Order order : processingOrders){
            order.setContractList(contractRepository.getContractsAfterDateByIdOrder(startDate,order.getId()));
        }
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
        //validate schedules
        Iterator<List<Contract>> currSchedule = schedules.iterator();
        while( currSchedule.hasNext() ){
            if( !validateSchedule(currSchedule.next()) ){
                currSchedule.remove();
            }
        }
        return  schedules;
    }

    private boolean validateSchedule( List<Contract> schedule ){
        Contract lastContract = null;
        for( Contract contract : schedule ){
            System.out.println("CONTARCT_ID:\t"+contract.getId());
            System.out.println("START_DATE:\t"+contract.getStart_date());
            if( lastContract == null ||
                lastContract.getStart_date()
                        .compareTo(contract.getStart_date()) <= 0  )
            {
                lastContract = contract;

            }
        }
        System.out.println("LAST_ID:\t"+lastContract.getId());
        System.out.println("LAST_START_DATE:\t"+lastContract.getStart_date());
        System.out.println("LAST_END_DATE:\t"+lastContract.getEnd_date().getTime());
        System.out.println("LAST_ORDER_DEADLINE:\t"+lastContract.getOrder().getDeadilne().getTime());
        return lastContract.getEnd_date()
                .compareTo(lastContract.getOrder().getDeadilne()) <= 0;
        //TODO: validate schedule
    }

    public void deleteContractsAfterStartDate(Date startDate){
        contractRepository.deleteContractsAfterDateAnd(startDate);
    }
    public void saveListOfContracts(List<Contract> contracts){
            contractRepository.save(contracts);
    }
}
