package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Order;
import com.model.Worker;
import com.repository.ContractRepository;
import com.repository.DetailRepository;
import com.repository.OrderRepository;
import com.repository.WorkerRepository;
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
        Map<Integer,Contract> lastContracts = new HashMap<>();
        for( Contract contract : schedule ){
            Integer orderId = contract.getOrder().getId();
            System.out.println("CONTARCT_ID:\t"+contract.getId());
            System.out.println("START_DATE:\t"+contract.getStart_date());
            if( lastContracts.get(orderId) == null ||
                lastContracts.get(orderId).getStart_date()
                        .compareTo(contract.getStart_date()) <= 0  )
            {
                lastContracts.put(orderId, contract);
            }
        }
        boolean isValid = true;
        for( Map.Entry<Integer,Contract> lastContractEntry : lastContracts.entrySet() ){
            Contract lastContract = lastContractEntry.getValue();
            System.out.println("LAST_ID:\t"+lastContract.getId());
            System.out.println("LAST_START_DATE:\t" + lastContract.getStart_date());
            System.out.println("LAST_END_DATE:\t"+lastContract.getEnd_date().getTime());
            System.out.println("LAST_ORDER_DEADLINE:\t"+lastContract.getOrder().getDeadilne().getTime());
            if( lastContract.getEnd_date()
                    .compareTo(lastContract.getOrder().getDeadilne()) > 0 )
            {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    public void deleteContractsAfterStartDate(Date startDate){
        contractRepository.deleteContractsAfterDateAnd(startDate);
    }
    public void saveListOfContracts(List<Contract> contracts){
        for(Contract contract : contracts){
            contractRepository.save(contract);
        }
    }
}
