package com.controller.planner;

import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.dhtmlx.planner.data.DHXDataFormat;
import com.model.Order;
import com.model.User;
import com.repository.ContractRepository;
import com.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by andron94 on 18.04.15.
 */

@Controller
@RequestMapping(value = "/planner")
public class PlannerSystemController {

    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private WorkerRepository workerRepository;
    //view orders page
    @RequestMapping(value = "viewOrders",method = RequestMethod.GET)
    public ModelAndView viewOrdersAction(){
        PagedListHolder<Order> man;
        User user=new User();
        return new ModelAndView("viewOrders","user",user);
    }
    @RequestMapping(value = "schedule",method = RequestMethod.GET)
    public ModelAndView scheduleAction(HttpServletRequest request)throws Exception{
            DHXPlanner s = new DHXPlanner("/resources/codebase/",DHXSkin.CLASSIC);
            s.setWidth(900);
            s.setInitialDate(2013, 0, 23);
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

