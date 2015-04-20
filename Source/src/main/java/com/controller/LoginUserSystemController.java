package com.controller;

import com.model.Order;
import com.controller.OrderDTO;

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
    private RegistrationService registrationService;

    @RequestMapping(value = "newOrder",method = RequestMethod.GET)
    public ModelAndView newOrderAction(){
        OrderDTO orderDTO = new OrderDTO();
        return new ModelAndView("newOrder","orderDTO", orderDTO);
    }

    @RequestMapping(value = "viewClientOrders",method = RequestMethod.GET)
    public ModelAndView myOrderAction(){
        Order order = new Order();
        return new ModelAndView("viewClientOrders","order", order);
    }

}



