package com.integration.manager;

import com.model.DetailType;
import com.model.Worker;
import com.repository.DetailTypeRepository;
import com.repository.WorkerRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by root on 12.04.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class WorkerDataTest extends Assert{

    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private DetailTypeRepository detailTypeRepository;

    Worker worker;
    List<DetailType> detailTypes;

    @Before
    public Worker initWorker(){
        worker=new Worker();
        worker.setCash(200);
        worker.setName("Garry");
        worker.setSename("Cooper");
        List<DetailType> specializations=detailTypeRepository.findAll();
        worker.setSpecializations(specializations);
        return worker;
    }
    @Test
    public void successSaveWorker(){
        workerRepository.save(worker);
        Worker savedWorker=workerRepository.findOne(worker.getId());
        assertEquals(savedWorker.getName(),"Garry");
        assertEquals(savedWorker.getSename(),"Cooper");
        assertEquals(savedWorker.getId(),worker.getId());
    }
    @After
    public void deleteTestWorker(){
        workerRepository.delete(worker.getId());
    }
}
