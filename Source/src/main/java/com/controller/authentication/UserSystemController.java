package com.controller.authentication;

import com.model.User;

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
@RequestMapping(value = "/") //mapping of pages
public class UserSystemController {
    @Autowired
    private RegistrationService registrationService;

  //REGISTRATION
    @RequestMapping(value = "registration",method = RequestMethod.GET)
    public ModelAndView registraionAction(){
        User user=new User();
        return new ModelAndView("registration","user",user);
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid User user,
             BindingResult result) {
        User registered=null;
        if (!result.hasErrors()) {
            registered = createUserAccount(user, result);
        }
        if (registered == null) {
            result.rejectValue("email", "emailExistError","Such email exist");
            return new ModelAndView("registration","user",user);
        }
        if(registrationService.sendActivationLetter(registered.getEmail(),registered.getKey())){
            return new ModelAndView("successRegistration");
        }
        else {
            result.rejectValue("letter", "letterSendError","Sorry we can`t write you");
            registrationService.deleteUser(registered.getIduser());
            return new ModelAndView("registration","user",user);
        }
    }
    private User createUserAccount(User user, BindingResult result) {
        User registered = null;
        try {
            registered = registrationService.registerNewUserAccount(user);
        } catch (RuntimeException e) {
            return null;
        }
        return registered;
    }

    @RequestMapping(value = "activation",method = RequestMethod.GET)
    public ModelAndView activationAction(@RequestParam(value = "key",required = true)String key){
        if(registrationService.activateUser(key)){
            return new ModelAndView("successActivation");
        }else {
            return new ModelAndView("failActivation");
        }

    }


    //LOGIN MODULE
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView loginAction(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }


    //ERROR MESSAGE FROM autorization
    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else if(exception instanceof DisabledException){
            error = "User is disabled!";
        }else {
            error = "Invalid username and password!";
        }
        return error;
    }

    // WHEN NO rights
    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("email", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }
}



