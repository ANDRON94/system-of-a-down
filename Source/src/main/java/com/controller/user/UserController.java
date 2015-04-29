package com.controller.user;

import com.controller.user.DTO.OrderDTO;
import com.model.Computer;
import com.model.Detail;
import com.model.Order;

import com.repository.OrderRepository;
import com.repository.StatusRepository;
import com.repository.UserRepository;
import com.service.ChoiceService;
import com.service.algorithms.schedule.ScheduleService;
import com.service.evolution.Unit;
import com.util.DateTimeFormatter;
import com.util.TodayManipulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping(value = "/user/") //mapping of pages
public class UserController {
    @Autowired
    private ChoiceService choiceService;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ScheduleService scheduleService;
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = "newOrder",method = RequestMethod.GET)
    public String newOrderAction(ModelMap modelMap){
       modelMap=initNewOrder(modelMap);
        //TODO finish and validate new order
        return "newOrder";
    }

    private ModelMap initNewOrder(ModelMap modelMap){
        OrderDTO newOrderDTO = new OrderDTO();
        newOrderDTO.setDeadilne(DateTimeFormatter.parseStringToDate(TodayManipulator.readToday()));
        newOrderDTO.setPrice(10000);
        newOrderDTO.setCount(2);

        Map<Integer,Integer> estimations = new HashMap<Integer, Integer>();
        for (int i = 1; i <= 5; i++) {
            estimations.put(i,i);
        }
        Map<Integer,String> countsDetail = new HashMap<Integer, String>();
        int count=0;
        for (int i = 0; i < 5; i++) {
            if(i==0){
                count=1;
            }else{
                countsDetail.put(count,count+"");
                count*=2;
            }
        }
        modelMap.addAttribute("estimations",estimations);
        modelMap.addAttribute("countsDetail", countsDetail);
        modelMap.addAttribute("orderDTO", newOrderDTO);
        return modelMap;
    }

    @RequestMapping(value = "newOrder",method = RequestMethod.POST)
    public String checkNewOrder(@ModelAttribute @Valid OrderDTO orderDTO,BindingResult result,ModelMap modelMap){
        if(result.hasErrors()){
            modelMap=initNewOrder(modelMap);
            modelMap.addAttribute("errors","Not valid data");
            return "newOrder";
        }
        if(orderDTO.getDeadilne().compareTo(DateTimeFormatter.parseStringToDate(TodayManipulator.readToday()))<=0){
            modelMap=initNewOrder(modelMap);
            modelMap.addAttribute("errors","This day ended or current! Please, enter future day!");
            return "newOrder";
        }
        System.out.println("New order posted!");
        //TODO catch new oder
        System.out.println(orderDTO.getRamCount());
        Unit unit=choiceService.makeChoice(orderDTO);
        if (unit == null) {
            System.out.println("Computer not found!");
            return "tryAgain";
        }
        else {
            Order order= new Order();
            Computer computer = new Computer();

            int produceTime=0;
            List<Detail> allDetails=new ArrayList<>();
            Detail currentDetail=unit.getDetails().get(0);
            produceTime+=orderDTO.getCpuCount()*currentDetail.getDetailType().getProduceTime();
            for (int i=0; i<orderDTO.getCpuCount();i++){
                   allDetails.add(currentDetail.clone());
            }
            currentDetail=unit.getDetails().get(1);
            produceTime+=orderDTO.getGpuCount()*currentDetail.getDetailType().getProduceTime();
            for (int i=0; i<orderDTO.getGpuCount();i++){
                allDetails.add(currentDetail.clone());
            }
            currentDetail=unit.getDetails().get(2);
            produceTime+=orderDTO.getMbCount()*currentDetail.getDetailType().getProduceTime();
            for (int i=0; i<orderDTO.getMbCount();i++){
                allDetails.add(currentDetail.clone());
            }
            currentDetail=unit.getDetails().get(3);
            produceTime+=orderDTO.getRamCount()*currentDetail.getDetailType().getProduceTime();
            for (int i=0; i<orderDTO.getRamCount();i++){
                allDetails.add(currentDetail.clone());
            }
            currentDetail=unit.getDetails().get(4);
            produceTime+=orderDTO.getHddCount()*currentDetail.getDetailType().getProduceTime();
            for (int i=0; i<orderDTO.getHddCount();i++){
                allDetails.add(currentDetail.clone());
            }
            computer.setDetailList(allDetails);
            order.setPerformance_time(produceTime*orderDTO.getCount());
            computer.setQuality(unit.getAverageQuality());
            computer.setPower(unit.getAveragePower());
            computer.setPrice(unit.getTotalPrise());
            order.setComputer(computer);
            order.setContractList(null);
            order.setDeadilne(orderDTO.getDeadilne());
            order.setPropouse(null);
            order.setCountComputers(1);
            order.setPrice(unit.getTotalPrise()*orderDTO.getCount());
            order.setCountComputers(orderDTO.getCount());
            order.setStatus(statusRepository.findOne(1));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            order.setUser(userRepository.findOneByEmail(auth.getName()));
            orderRepository.save(order);
            System.out.println("Computer found! Works starts!");

            //TODO catch list of details for order
            return "redirect:/user/viewClientOrders";
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

    @RequestMapping(value = "cancel/{orderId}",method = RequestMethod.GET)
    public String cancelOrderAction(@PathVariable Integer orderId ){
        Order order=orderRepository.findOne(orderId);
        Date startDate = scheduleService.initStartDate();
        scheduleService.deleteContractsAfterStartDate(startDate);
        System.out.println("Delete old contracts");
        scheduleService.saveListOfContracts(scheduleService.schedule(null,startDate).get(0));
        System.out.println("Save new contracts");
        order.setStatus(statusRepository.findOneByName("USER_CANCEL"));
        orderRepository.save(order);
        return "redirect:/user/viewClientOrders";
    }
}



