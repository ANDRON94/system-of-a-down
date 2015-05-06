package com.controller.planner;

import com.model.Contract;
import com.model.Order;
import com.model.Schedule;
import com.service.OrderService;
import com.service.algorithms.schedule.ScheduleService;
import com.util.DateTimeFormatter;
import com.util.MailUtil;
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
        Date startDate = scheduleService.initStartDate();
        List<List<Contract>> scheduleContractVariants = scheduleService.schedule(newOrder,startDate);
        if(scheduleContractVariants.isEmpty()){
            newOrder.setStatus(orderService.findStatusByName("SYSTEM_CANCEL"));
            newOrder.setPropouse("Sorry we can`t perform this order. But if you increase deadline for few days,we`ll have done it");
            orderService.saveOrder(newOrder);
            MailUtil.sendMail(newOrder.getUser().getEmail(),"Order decline!","Hello, dear "
                    +newOrder.getUser().getFirstName()
                    +" "+ newOrder.getUser().getLastName()+"!\n \t Your order is declined! Sorry. For more details <a href=\"http://localhost:8080/user/viewOrder/"+newOrder.getId()+"\">Activation Link</a>\"  \n\n\n \tCalendar system.");
            return "redirect:/planner/viewOrders/1";

        }
        else {
            newOrder.setStatus(orderService.findStatusByName("IN_QUEUE"));
            orderService.saveOrder(newOrder);
            List<Schedule> scheduleList= new ArrayList<>();
           List<Double> cashes= scheduleService.getCaches(scheduleContractVariants);
            for (int i = 0; i < scheduleContractVariants.size(); i++) {
                //TODO OPTIMIZE TO FOREACH
                Schedule newSchedule= new Schedule(scheduleContractVariants.get(i),orderService.getAllWorkers(),request);
                newSchedule.setCash(cashes.get(i));
                scheduleList.add(newSchedule);
            }
            int optimizeSchedule = scheduleService.getIndexOptimizeSchedule(cashes);


            scheduleService.deleteContractsAfterStartDate(startDate);
            System.out.println("Delete old contracts");
            modelMap.addAttribute("optimum", optimizeSchedule);
            request.getSession().setAttribute("ScheduleVariants", scheduleContractVariants);
            scheduleService.saveListOfContracts(scheduleContractVariants.get(optimizeSchedule));
            System.out.println("Save new contracts");
            MailUtil.sendMail(newOrder.getUser().getEmail(),"Order approved!","Hello, dear "
                    +newOrder.getUser().getFirstName()
                    +" "+ newOrder.getUser().getLastName()+"!\n \t Your order is approved! Thanks for order!\n\n\n \tCalendar system.");
            modelMap.addAttribute("scheduleVariants",scheduleList);
            return "scheduleVariants";
        }
    }

    @RequestMapping(value = "/scheduleVariant/{variantId}", method = RequestMethod.GET)
    public ModelAndView viewOrderAction(@PathVariable Integer variantId,HttpServletRequest request ){
        List<List<Contract>> variants=(List<List<Contract>>)request.getSession().getAttribute("ScheduleVariants");
        Schedule schedule = new Schedule(variants.get(variantId),scheduleService.getAllWorkers(),request);
        try {
            request.setAttribute("planner",schedule.getPlanner().render());
        } catch (Exception e) {
            request.setAttribute("planner",null);
        }
        return  new ModelAndView("schedule");
    }

    @RequestMapping(value = "/viewOrder/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrderAction(@PathVariable Integer orderId ){
        Order order = orderService.getOrderById(orderId);
        return new ModelAndView("viewOrder","order",order);
    }
}
