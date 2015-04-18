package com.controller.manager;


import com.controller.manager.DTO.WorkerDTO;
import com.model.DetailType;
import com.model.Worker;

import com.repository.DetailTypeRepository;
import com.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/manager") //mapping of pages
public class WorkerController {

    public static final String TO_DETAILS_LIST="redirect:/manager/workers";
    //repository for CRUD
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private DetailTypeRepository detailTypeRepository;


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
        List<Worker> workers= workerRepository.findAll();//all workers
        model.addObject("workers",workers);
        model.setViewName("workers");
        return model; //see first action
    }
    //generate form to edit
    @RequestMapping(value="editWorker/{id}",method = RequestMethod.GET)
    public ModelAndView editWorkerAction(@PathVariable Integer id) {
        Worker worker= workerRepository.findOne(id);
        WorkerDTO workerDTO = new WorkerDTO();
        workerDTO.setName(worker.getName());
        workerDTO.setCash(worker.getCash());
        workerDTO.setSename(worker.getSename());
        workerDTO.setSpecializations(new ArrayList<Integer>());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editWorker");
        List<DetailType> types =  detailTypeRepository.findAll();
        modelAndView.addObject("specializationTypes",types);
        modelAndView.addObject("workerForm",worker);
        return modelAndView;
    }
    //handling edit form before to edit
    @RequestMapping(value="editWorker/{id}",method = RequestMethod.POST)
    public String updateWorkerAction(@PathVariable Integer id,@ModelAttribute("workerForm") WorkerDTO workerDTO) {
        Worker worker= workerRepository.findOne(id);
        worker.setId(id);
        worker.setName(workerDTO.getName());
        worker.setCash(workerDTO.getCash());
        worker.setSename(workerDTO.getSename());
        List<DetailType> spec= new ArrayList<DetailType>();
        for(Integer idSpec : workerDTO.getSpecializations()){
            spec.add(detailTypeRepository.findOne(idSpec));
        }
        worker.setSpecializations(spec);
        workerRepository.save(worker);
        return TO_DETAILS_LIST;
    }
    //generate form to create
    @RequestMapping(value = "createWorker",method = RequestMethod.GET)
    public ModelAndView createWorkerAction() {
        WorkerDTO worker = new WorkerDTO();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createWorker");
        List<DetailType> types =  detailTypeRepository.findAll();
        modelAndView.addObject("specializationTypes",types);
        modelAndView.addObject("workerForm",worker);
        return modelAndView;
    }
    //handling create form before to save
    @RequestMapping(value = "saveWorker",method = RequestMethod.POST)
    public String saveWorkerAction(@ModelAttribute("workerForm") WorkerDTO workerDTO) {
        Worker worker=new Worker();
        worker.setName(workerDTO.getName());
        worker.setCash(workerDTO.getCash());
        worker.setSename(workerDTO.getSename());
        List<DetailType> spec= new ArrayList<DetailType>();
        for(Integer id : workerDTO.getSpecializations()){
            spec.add(detailTypeRepository.findOne(id));
        }
        worker.setSpecializations(spec);
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