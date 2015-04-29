package com.controller.planner;

import com.service.OrderService;
import com.util.TodayManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 28.04.15.
 */
@Controller
@RequestMapping(value = "/planner")
public class BackToTheFutureController {
    @Autowired
    private OrderService orderService;
    @RequestMapping(value = "changeTodayDate",method = RequestMethod.GET)
    public ModelAndView scheduleAction(){

        return new ModelAndView("changeDate","date", TodayManipulator.readToday());
    }
    @RequestMapping(value = "changeTodayDate", method = RequestMethod.POST)
    public String changeDateAction(@RequestParam("dateToday")Date date ,ModelMap modelMap){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        System.out.println(date);
        Date oldDate=null;
        try {
            oldDate= dateFormat.parse(TodayManipulator.readToday());
        } catch (ParseException e) {

            e.printStackTrace();
        }
        System.out.println(oldDate);
        if(date.compareTo(oldDate)<=0){
            modelMap.addAttribute("error","This day ended or current! Please, enter future day!");
            modelMap.addAttribute("date",TodayManipulator.readToday());
            return "changeDate";
        }
        else{
            TodayManipulator.writeToday(dateFormat.format(date));
            orderService.backToTheFuture(date);
            return "redirect:/planner/viewOrders/1";
        }
    }
}
