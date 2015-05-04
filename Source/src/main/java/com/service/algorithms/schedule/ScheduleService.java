package com.service.algorithms.schedule;

import com.model.Contract;
import com.model.Detail;
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

    public Date initStartDate(){
        Date startDate = DateTimeFormatter.parseStringToDate(TodayManipulator.readToday());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        return calendar.getTime();
    }
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

        if(newOrder!=null){
            works.add(newOrder);
        }

        AmazingScheduling scheduler = new AmazingScheduling(startDate,freeWorkers);
        //first algorithm
        List<Order> firstWorks = new ArrayList<>(works);
        DeadlineWorkCriteria deadlineWorkCriteria = new DeadlineWorkCriteria(firstWorks);
        DurationOperationCriteria durationOperationCriteria = new DurationOperationCriteria();
        BusyWorkerCriteria busyWorkerCriteria = new BusyWorkerCriteria(freeWorkers, null);

        schedules.add( scheduler.makeSchedule(
                deadlineWorkCriteria,
                durationOperationCriteria,
                busyWorkerCriteria
        ));
        System.out.println( "Count of works after first: " + works.size() );
        //second algorithm
        List<Order> secondWorks = new ArrayList<>(works);
        //update duration for processing orders
        for( Order order : secondWorks ){
            if( order.getStatus().getName() == "IN_PROSESS" ){
                int orderPerfomanceTime = 0;
                for(Contract contract : order.getContractList() ){
                    orderPerfomanceTime += contract.
                            getDetail().
                            getDetailType().
                            getProduceTime();
                }
                order.setPerformance_time(orderPerfomanceTime);
            }
        }
        DeadlineDurationRatioWorkCriteria deadlineDurationRatioWorkCriteria =
                new DeadlineDurationRatioWorkCriteria( secondWorks );
        busyWorkerCriteria.restart();

        schedules.add( scheduler.makeSchedule(
                deadlineDurationRatioWorkCriteria,
                durationOperationCriteria,
                busyWorkerCriteria
        ));
        //third algorithm
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
            contractRepository.save(contracts);
    }
    public List<Double> getCaches( List<List<Contract>> schedules){
        TotalSalaryOptimizeCashCriteria tsoc = new TotalSalaryOptimizeCashCriteria();
        List<Double> cashes= new ArrayList<>();
        for (List<Contract> variant : schedules){
            double salary=tsoc.calcScheduleTotalSalary(variant);
            System.out.println("SALARY:\t" +salary);
            cashes.add(salary);
        }
        System.out.println();
        return cashes;
    }

    public int getIndexOptimizeSchedule( List<Double> cashes ){
        TotalSalaryOptimizeCashCriteria tsoc = new TotalSalaryOptimizeCashCriteria();
        return tsoc.findOptimizeScheduleByCash(cashes);
    }
    public List<Worker> getAllWorkers(){
        return workerRepository.findAll();
    }
}
