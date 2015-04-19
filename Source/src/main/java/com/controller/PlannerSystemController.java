package com.controller;

import com.model.Order;
import com.model.User;
import com.service.OrderService;
import com.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by andron94 on 18.04.15.
 */

@Controller
@RequestMapping(value = "/planner")
public class PlannerSystemController {
    @Autowired
    private OrderService orderService;

    //view orders page
    @RequestMapping(value = "/viewOrders/{pageNumber}",method = RequestMethod.GET)
    public ModelAndView viewOrdersAction(@PathVariable Integer pageNumber){
        PageWrapper<Order> page = new PageWrapper<Order>(
            orderService.getAllOrders(pageNumber)
        );
        return new ModelAndView("viewOrders","page",page);
    }
}

