package com.controller.planner;

import com.model.Contract;
import com.model.Order;
import com.model.Schedule;
import com.service.OrderService;
import com.service.algorithms.schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 28.04.15.
 */
@Controller
@RequestMapping(value = "/planner")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "planSchedule/{orderId}", method = RequestMethod.GET)
    public ModelAndView planScheduleAction(@PathVariable Integer orderId,HttpServletRequest request){
       Order newOrder= orderService.getOrderById(orderId);
       List<List<Contract>> scheduleContractVariants = scheduleService.schedule(newOrder);
        List<Schedule> scheduleList= new ArrayList<>();
        int id=100;
        for(List<Contract> variant :scheduleContractVariants){
            for(Contract contract:variant){
                contract.setId(id++);
                System.out.println(contract.getId());
                System.out.println(contract.getStart_date().toString());
                System.out.println(contract.getEnd_date().toString());
                System.out.println(contract.getDetail().getName());
                System.out.println(contract.getOrder().getId());
                System.out.println();
            }
            Schedule newSchedule= new Schedule(variant,orderService.getAllWorkers(),request);

            scheduleList.add(newSchedule);
        }
       return new ModelAndView("scheduleVariants","scheduleVariants",scheduleList);
    }

    @RequestMapping(value = "/viewOrder/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrderAction(@PathVariable Integer orderId ){
        Order order = orderService.getOrderById(orderId);
        return new ModelAndView("viewOrder","order",order);
    }
}
