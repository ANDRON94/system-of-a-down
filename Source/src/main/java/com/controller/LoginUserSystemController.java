package com.controller;

import com.model.Order;
import com.controller.OrderDTO;

import com.service.ChoiceService;
import com.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



@Controller
@RequestMapping(value = "/user/") //mapping of pages
public class LoginUserSystemController {
    @Autowired
    private ChoiceService choiceService;

    @RequestMapping(value = "newOrder",method = RequestMethod.GET)
    public ModelAndView newOrderAction(){
        OrderDTO orderDTO = new OrderDTO();
        //TODO finish and validate new order
        return new ModelAndView("newOrder","orderDTO", orderDTO);
    }


    @RequestMapping(value = "newOrder",method = RequestMethod.POST)
    public ModelAndView checkNewOrder(){
        System.out.println("New order posted!");
        //TODO catch new oder
        OrderDTO orderDTO = new OrderDTO();
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
    public ModelAndView myOrderAction(){
        //TODO view of client orders
        return new ModelAndView("viewClientOrders");
    }

}



