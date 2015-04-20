package com.controller.planner;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.model.Order;
import com.repository.ContractRepository;
import com.repository.WorkerRepository;
import com.service.OrderService;
import com.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by andron94 on 18.04.15.
 */

@Controller
@RequestMapping(value = "/planner")
public class PlannerSystemController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private WorkerRepository workerRepository;
    //view orders page
    @RequestMapping(value = "/viewOrders/{pageNumber}",method = RequestMethod.GET)
    public ModelAndView viewOrdersAction(@PathVariable Integer pageNumber){
        PageWrapper<Order> page = new PageWrapper<Order>(
            orderService.getAllOrders(pageNumber)
        );
        return new ModelAndView("viewOrders","page",page);
    }

    @RequestMapping(value = "/viewOrder/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrderAction(@PathVariable Integer orderId ){
        Order order = orderService.getOrderById(orderId);
        return new ModelAndView("viewOrder","order",order);
    }

    @RequestMapping(value = "schedule",method = RequestMethod.GET)
    public ModelAndView scheduleAction(HttpServletRequest request)throws Exception{
            DHXPlanner s = new DHXPlanner("/resources/codebase/",DHXSkin.CLASSIC);
            s.setWidth(900);
            s.setInitialDate(new Date());
            s.views.clear();
            s.load("/planner/09_events", DHXDataFormat.JSON);
            s.data.dataprocessor.setURL("/planner/09_events");
            DHXTimelineView view = new DHXTimelineView("topic", "event_topic", "Topic");
            view.setRenderMode(DHXTimelineView.RenderModes.BAR);
            view.setXScaleUnit(DHXTimelineView.XScaleUnits.MINUTE);
            view.setXStep(30);
            view.setXSize(16);
            view.setXStart(20);
            view.setXLength(48);
            view.setServerList("topic");
            s.views.add(view);
            s.setInitialView("topic");
            s.calendars.attachMiniCalendar();
        request.setAttribute("planner",s.render());
        return  new ModelAndView("schedule");
    }
    @RequestMapping("/09_events")
    @ResponseBody
    public String events_09(HttpServletRequest request)
    {
        EventsManager eventsManager = new EventsManager(request);
        return eventsManager.start(contractRepository.findAll(),workerRepository.findAll());
    }
}

