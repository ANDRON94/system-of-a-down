package com.model;

import com.controller.planner.EventsManager;
import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXMiniCalendar;
import com.dhtmlx.planner.controls.DHXTimelineUnit;
import com.dhtmlx.planner.controls.DHXTimelineView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 28.04.15.
 */
public class Schedule {
    private EventsManager eventsManager;
    private DHXPlanner s;

    public Schedule(List<Contract> contracts, List<Worker> workers,HttpServletRequest request) {
        s = new DHXPlanner("/resources/codebase/", DHXSkin.CLASSIC);
        s.setWidth(900);
        s.setInitialDate(new Date());
        s.views.clear();
        eventsManager = new EventsManager(request);
        eventsManager.initContracts(contracts);
        s.parse(eventsManager.getEvents());
        DHXTimelineView view = new DHXTimelineView("topic", "event_topic", "Topic");
        view.setRenderMode(DHXTimelineView.RenderModes.BAR);
        view.setXScaleUnit(DHXTimelineView.XScaleUnits.MINUTE);
        for(Worker worker :workers){
            DHXTimelineUnit unit= new DHXTimelineUnit(worker.getId()+"", worker.getSename());
            view.addOption(unit);
        }
        view.setXStep(1);
        view.setXSize(16);
        view.setXStart(500);
        view.setXLength(0);
        view.setServerList("topic");
        s.views.add(view);
        s.setInitialView("topic");
        s.calendars.attachMiniCalendar();

    }
    public DHXPlanner getPlanner(){
        return s;
    }
}
