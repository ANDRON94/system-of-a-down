package com.model;

import com.controller.planner.EventsManager;
import com.dhtmlx.planner.DHXPlanner;
import com.dhtmlx.planner.DHXSkin;
import com.dhtmlx.planner.controls.DHXMiniCalendar;
import com.dhtmlx.planner.controls.DHXTimelineUnit;
import com.dhtmlx.planner.controls.DHXTimelineView;
import com.util.DateTimeFormatter;
import com.util.TodayManipulator;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 28.04.15.
 */
public class Schedule {
    private EventsManager eventsManager;
    private DHXPlanner s;
    private Double cash;

    public Schedule(List<Contract> contracts, List<Worker> workers,HttpServletRequest request) {
        s = new DHXPlanner("/resources/codebase/", DHXSkin.CLASSIC);
        s.setWidth(900);
        s.setInitialDate(DateTimeFormatter.parseStringToDate(TodayManipulator.readToday()));
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
        view.setXStep(10);
        view.setXSize(16);
        view.setXStart(60);
        view.setXLength(0);
        view.setServerList("topic");
        s.views.add(view);
        s.setInitialView("topic");
        s.calendars.attachMiniCalendar();

    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public DHXPlanner getPlanner(){
        return s;
    }
}
