package com.controller.planner;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXTimelineUnit;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.dhtmlx.planner.data.DHXCollection;
import com.model.Order;
import com.model.Schedule;
import com.model.Worker;
import com.repository.ContractRepository;
import com.repository.WorkerRepository;
import com.service.OrderService;
import com.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andron94 on 18.04.15.
 */

@Controller
@RequestMapping(value = "/planner")
public class PlannerController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }


    //view orders page
    @RequestMapping(value = "/viewOrders/{pageNumber}",method = RequestMethod.GET)
    public ModelAndView viewOrdersAction(@PathVariable Integer pageNumber){
        PageWrapper<Order> page = new PageWrapper<Order>(
            orderService.getAllOrders(pageNumber)
        );
        return new ModelAndView("viewOrders","page",page);
    }




    @RequestMapping(value = "schedule",method = RequestMethod.GET)
    public ModelAndView scheduleAction(HttpServletRequest request)throws Exception{
        Schedule schedule = new Schedule(contractRepository.findAll(),workerRepository.findAll(),request);
        request.setAttribute("planner",schedule.getPlanner().render());
        return  new ModelAndView("schedule");
    }

}

