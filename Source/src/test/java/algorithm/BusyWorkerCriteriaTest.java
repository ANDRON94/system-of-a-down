package algorithm;

import com.model.Detail;
import com.model.DetailType;
import com.model.Order;
import com.model.Worker;
import com.repository.OrderRepository;
import com.repository.WorkerRepository;
import com.service.algorithms.schedule.BusyWorkerCriteria;
import com.service.algorithms.schedule.DurationOperationCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by andron94 on 27.04.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class BusyWorkerCriteriaTest {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void testBusyWorkerCriteria(){
        Date date = null;
        try {
            date= new SimpleDateFormat("yyyy-mm-dd").parse("2015-04-23 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Worker> workers = workerRepository.findAll();
        BusyWorkerCriteria busyWorkerCriteria = new BusyWorkerCriteria(workers,null);
        Order order = orderRepository.findByStatus_Name("NEW_ORDER").get(0);
        DurationOperationCriteria durationOperationCriteria = new DurationOperationCriteria();
        List<Detail> details = durationOperationCriteria.find(order);
        for (Detail detail : details) {
            Worker worker = busyWorkerCriteria.find(detail);
            System.out.println("Detail Id: " + detail.getId());
            System.out.println("Detail Type: "+ detail.getDetailType().getName() );
            System.out.println("Detail Time: "+ detail.getDetailType().getProduceTime() );

            System.out.println("Worker Name: " + worker.getName() );
            System.out.println("Wokrer spec: " );
            for( DetailType spec : worker.getSpecializations() ){
                System.out.print(spec.getName() + " ");
            }
            System.out.println();
        }
    }
}
