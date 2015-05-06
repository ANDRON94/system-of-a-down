package algorithm;

import com.model.Detail;
import com.model.DetailType;
import com.model.Order;
import com.model.Worker;
import com.repository.OrderRepository;
import com.repository.WorkerRepository;
import com.service.algorithms.schedule.DurationOperationCriteria;
import com.service.algorithms.schedule.ExpectancyOperationCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by andron94 on 06.05.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class ExpectancyOperationCriteriaTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    WorkerRepository workerRepository;

    @Test
    public void testExpectancyOperationCriteria(){
        List<Order> orders = orderRepository.findByStatus_Name("NEW_ORDER");
        System.out.println("Orders: " + orders.size());
        for( Order order : orders) {
            System.out.println("Order Id:" + order.getId());
            ExpectancyOperationCriteria expectancyOperationCriteria = new ExpectancyOperationCriteria(
                    orders, workerRepository.findAll()
            );
            List<Detail> details = expectancyOperationCriteria.find(order);
            for (Detail detail : details) {
                System.out.println("Detail: " + detail.getId());
                System.out.println("Detail name : " + detail.getDetailType().getName());
                System.out.println("Prdoduce time: " + detail.getDetailType().getProduceTime());
            }
        }

    }
}
