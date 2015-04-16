package com.controller.manager;


import com.model.Worker;

import com.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping(value = "/user") //mapping of pages
public class WorkerController {

    public static final String TO_DETAILS_LIST="redirect:/user/workers";
    //repository for CRUD
    @Autowired
    private WorkerRepository workerRepository;


    //Action with REST parametr,Get - method
    @RequestMapping(value = "viewWorker/{id}",method = RequestMethod.GET)
    public ModelAndView viewWorkerAction(@PathVariable Integer id){
        Worker worker = workerRepository.findOne(id); //find by id worker
        return new ModelAndView("viewWorker","worker",worker);
        //render page for template "viewWorker"
        // with params to this template
    }
    //all workers action
    @RequestMapping(value="workers")
    public ModelAndView workersAction() {
        ModelAndView model=new ModelAndView();
        //List<Worker> workers= workerRepository.findAll();//all workers
        List<Worker> workers=null;
        model.addObject("workers",workers);
        model.setViewName("workers");
        return model; //see first action
    }
    //generate form to edit
    @RequestMapping(value="editWorker/{id}",method = RequestMethod.GET)
    public ModelAndView editWorkerAction(@PathVariable Integer id,ModelMap model) {
        Worker worker= workerRepository.findOne(id);
        return new ModelAndView("editWorker", "workerForm", worker);
    }
    //handling edit form before to edit
    @RequestMapping(value="editWorker/{id}",method = RequestMethod.POST)
    public String updateWorkerAction(@PathVariable Integer id,@ModelAttribute("workerForm") Worker worker) {
        Worker updateWorker= workerRepository.findOne(id);
        //  updateWorker.setName(worker.setName());
        // updateWorker.setData(worker.getData());
        workerRepository.save(updateWorker);
        return TO_DETAILS_LIST;
    }
    //generate form to create
    @RequestMapping(value = "createWorker",method = RequestMethod.GET)
    public ModelAndView createWorkerAction() {
        Worker worker = new Worker();
        return new ModelAndView("createWorker","workerForm",worker);
    }
    //handling create form before to save
    @RequestMapping(value = "saveWorker",method = RequestMethod.POST)
    public String saveWorkerAction(@ModelAttribute("workerForm") Worker worker) {
        workerRepository.save(worker);
        return TO_DETAILS_LIST;
    }
    //delete worker and redirect page
    @RequestMapping(value = "deleteWorker/{id}",method = RequestMethod.GET)
    public String deleteWorkerAction(@PathVariable Integer id){
        workerRepository.delete(id);
        return TO_DETAILS_LIST;
    }
}