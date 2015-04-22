package com.controller;

import com.model.Order;
import com.controller.OrderDTO;

import com.model.User;
import com.service.ChoiceService;
import com.service.RegistrationService;
import com.util.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/user/") //mapping of pages
public class LoginUserSystemController {
    @Autowired
    private ChoiceService choiceService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = "newOrder",method = RequestMethod.GET)
    public ModelAndView newOrderAction(){
        OrderDTO orderDTO = new OrderDTO();
        Map<Integer,Integer> estimations = new HashMap<Integer, Integer>();
        for (int i = 1; i <= 5; i++) {
            estimations.put(i,i);
        }
        Map<Integer,String> countsDetail = new HashMap<Integer, String>();
        int count=0;
        for (int i = 0; i < 5; i++) {
            if(i==0){
                countsDetail.put(count,"None");
                count=1;
            }else{
                countsDetail.put(count,count+"");
                count*=2;
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newOrder");
        modelAndView.addObject("estimations",estimations);
        modelAndView.addObject("countsDetail",countsDetail);
        modelAndView.addObject("orderDTO", orderDTO);

        //TODO finish and validate new order
        return modelAndView;
    }


    @RequestMapping(value = "newOrder",method = RequestMethod.POST)
    public ModelAndView checkNewOrder(@ModelAttribute OrderDTO orderDTO){
        System.out.println("New order posted!");
        //TODO catch new oder
        System.out.println(orderDTO.getRamCount());

        if (choiceService.makeChoice(orderDTO) == null) {
            System.out.println("Computer not found!");
            return new ModelAndView("tryAgain");
        }
        else {
            System.out.println("Computer found! Works starts!");
            //TODO catch list of details for order
            return new ModelAndView("viewClientOrders");
        }

    }

    @RequestMapping(value = "viewClientOrders",method = RequestMethod.GET)
    public ModelAndView myOrdersAction(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Order> orders= choiceService.findOrdersOfClient(auth.getName());
        //TODO view of client orders
        return new ModelAndView("viewClientOrders","orders",orders);
    }
    @RequestMapping(value = "viewOrder/{orderId}", method = RequestMethod.GET)
    public ModelAndView viewOrderAction(@PathVariable Integer orderId ){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Order order = choiceService.findOrderByIdAndEmail(orderId, auth.getName());
        if(order!=null){
            return new ModelAndView("viewOrderUser","order",order);

        }else {
            return new ModelAndView("403");
        }
    }

}



