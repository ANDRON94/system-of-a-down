package com.controller;

import com.model.Order;
import com.model.User;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andron94 on 18.04.15.
 */

@Controller
@RequestMapping(value = "/planner")
public class PlannerSystemController {

    //view orders page
    @RequestMapping(value = "viewOrders",method = RequestMethod.GET)
    public ModelAndView viewOrdersAction(){
        PagedListHolder<Order> man;
        User user=new User();
        return new ModelAndView("viewOrders","user",user);
    }
}

