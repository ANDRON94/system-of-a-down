package com.controller.planner;

import com.dhtmlx.planner.DHXEv;
import com.dhtmlx.planner.DHXEventsManager;
import com.dhtmlx.planner.data.DHXCollection;
import com.model.Contract;
import com.model.Worker;
import com.repository.ContractRepository;
import com.repository.WorkerRepository;
import com.model.EventUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 29.03.15.
 */
public class EventsManager extends DHXEventsManager {


    private List<Contract> contracts;
    private List<Worker> workers;
    public EventsManager(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Iterable<DHXEv> getEvents() {
        ArrayList <DHXEv> events = new ArrayList<DHXEv>();
        for(Contract contract :  contracts){
            EventUnits ev1 = new EventUnits();
            ev1.setId(contract.getId());
            ev1.setStart_date(contract.getStart_date());
            ev1.setEnd_date(contract.getEnd_date());
            ev1.setText(contract.getOrder().getId() + "\n" + contract.getDetail().getName());
            ev1.setEvent_topic(contract.getWorker().getId() + "");
            events.add(ev1);
        }

        return events;
    }

    @Override
    public HashMap<String, Iterable<DHXCollection>> getCollections()
    {

        ArrayList<DHXCollection> event_type = new ArrayList<DHXCollection>();

        for(Worker worker :workers){
            DHXCollection unit= new DHXCollection(worker.getId()+"", worker.getSename());
            event_type.add(unit);

        }

        HashMap<String, Iterable<DHXCollection>> c = new HashMap<String, Iterable<DHXCollection>>();
        c.put("topic", event_type);
        return c;
    }

    public String start(List<Contract> contracts,List<Worker> workers){
        this.contracts=contracts;
        this.workers=workers;
        return  this.run();
    }
}
