package com.dao;

import com.model.DetailType;
import com.model.Worker;
import com.repository.DetailTypeRepository;
import com.repository.WorkerRepository;
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
    /*
    @Autowired
    private WorkerRepository repository;
    @Autowired
    private DetailTypeRepository detailTypeRepository;

    Worker worker;
    List<DetailType> detailTypes;

    @Before
    public void initWorker(){
        worker=new Worker();
    }*/
    @Test
    public void temp(){

    }
}
