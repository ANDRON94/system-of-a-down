package algorithm;

import com.model.Contract;
import com.model.Order;
import com.repository.OrderRepository;
import com.service.algorithms.schedule.ScheduleService;
import com.util.DateTimeFormatter;
import com.util.TodayManipulator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by andron94 on 28.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class CreateScheduleTest extends Assert {

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    OrderRepository orderRepository;


    private Date initStartDate(){
        Date startDate = DateTimeFormatter.parseStringToDate(TodayManipulator.readToday());
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        return calendar.getTime();
    }

    @Test
    public void testCreateSchedule(){
        List<Order> newOrders = orderRepository.findByStatus_Name("NEW_ORDER");
        assertEquals(1, newOrders.size());
        List<List<Contract>> schedules = scheduleService.schedule(newOrders.get(0),initStartDate());
        for( List<Contract> schedule : schedules ){
            for ( Contract contract : schedule ){
                System.out.println( "Order Id: " + contract.getOrder().getId() );
                System.out.println( "Detail Name: " + contract.getDetail().getName() );
                System.out.println( "Worker Name: " + contract.getWorker().getName() );
                System.out.println("Start: " + contract.getStart_date());
                System.out.println("End: " + contract.getEnd_date());
                System.out.print("\n");
            }
            System.out.print("\n\n\n");
        }
    }
}
