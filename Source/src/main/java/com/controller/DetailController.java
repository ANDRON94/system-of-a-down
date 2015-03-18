package com.controller;


import com.model.Detail;

import com.repository.DetailRepository;
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
public class DetailController {

    public static final String TO_DETAILS_LIST="redirect:/user/details";
    //repository for CRUD
    @Autowired
    private DetailRepository detailRepository;


    //Action with REST parametr,Get - method
    @RequestMapping(value = "viewDetail/{id}",method = RequestMethod.GET)
    public ModelAndView viewDetailAction(@PathVariable Integer id){
        Detail detail = detailRepository.findOne(id); //find by id detail
        return new ModelAndView("viewDetail","detail",detail);
         //render page for template "viewDetail"
        // with params to this template
    }
    //all details action
    @RequestMapping(value="details")
    public ModelAndView detailsAction() {
        ModelAndView model=new ModelAndView();
        //List<Detail> details= detailRepository.findAll();//all details
        List<Detail> details=null;
        model.addObject("details",details);
        model.setViewName("details");
        return model; //see first action
    }
    //generate form to edit
    @RequestMapping(value="editDetail/{id}",method = RequestMethod.GET)
    public ModelAndView editDetailAction(@PathVariable Integer id,ModelMap model) {
        Detail detail= detailRepository.findOne(id);
        return new ModelAndView("editDetail", "detailForm", detail);
    }
    //handling edit form before to edit
    @RequestMapping(value="editDetail/{id}",method = RequestMethod.POST)
    public String updateDetailAction(@PathVariable Integer id,@ModelAttribute("detailForm") Detail detail) {
        Detail updateDetail= detailRepository.findOne(id);
      //  updateDetail.setName(detail.setName());
       // updateDetail.setData(detail.getData());
        detailRepository.save(updateDetail);
        return TO_DETAILS_LIST;
    }
    //generate form to create
    @RequestMapping(value = "createDetail",method = RequestMethod.GET)
    public ModelAndView createDetailAction() {
        Detail detail = new Detail();
        return new ModelAndView("createDetail","detailForm",detail);
    }
     //handling create form before to save
    @RequestMapping(value = "saveDetail",method = RequestMethod.POST)
    public String saveDetailAction(@ModelAttribute("detailForm") Detail detail) {
        detailRepository.save(detail);
        return TO_DETAILS_LIST;
    }
    //delete detail and redirect page
    @RequestMapping(value = "deleteDetail/{id}",method = RequestMethod.GET)
    public String deleteDetailAction(@PathVariable Integer id){
        detailRepository.delete(id);
        return TO_DETAILS_LIST;
    }
}