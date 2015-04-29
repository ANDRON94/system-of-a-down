package com.controller.planner;

import com.model.Contract;
import com.model.Order;
import com.model.Schedule;
import com.service.OrderService;
import com.service.algorithms.schedule.ScheduleService;
import com.util.DateTimeFormatter;
import com.util.TodayManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public String planScheduleAction(@PathVariable Integer orderId,HttpServletRequest request,ModelMap modelMap){
       Order newOrder= orderService.getOrderById(orderId);
        Date startDate = initStartDate();
        List<List<Contract>> scheduleContractVariants = scheduleService.schedule(newOrder,startDate);
        if(scheduleContractVariants.isEmpty()){
            newOrder.setStatus(orderService.findStatusByName("PRE_CANCEL"));
            orderService.saveOrder(newOrder);
            return "redirect:/planner/viewOrders/1";
        }
        else {
            newOrder.setStatus(orderService.findStatusByName("IN_QUEUE"));
            orderService.saveOrder(newOrder);
            List<Schedule> scheduleList= new ArrayList<>();
            for(List<Contract> variant : scheduleContractVariants){
                Schedule newSchedule= new Schedule(variant,orderService.getAllWorkers(),request);
                scheduleList.add(newSchedule);
            }
            scheduleService.deleteContractsAfterStartDate(startDate);
            scheduleService.saveListOfContracts(scheduleContractVariants.get(0));
            modelMap.addAttribute("scheduleVariants",scheduleList);
            return "scheduleVariants";
        }


    }
    private Date initStartDate(){
        Date startDate = DateTimeFormatter.parseStringToDate(TodayManipulator.readToday());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        return calendar.getTime();
    }
    @RequestMapping(value = "/viewOrder/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrderAction(@PathVariable Integer orderId ){
        Order order = orderService.getOrderById(orderId);
        return new ModelAndView("viewOrder","order",order);
    }
}
