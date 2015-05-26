package com.integration.manager;

import com.model.Detail;
import com.model.DetailType;
import com.model.User;
import com.repository.DetailRepository;
import com.repository.DetailTypeRepository;
import com.repository.UserRepository;
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
public class DetailDataTest extends Assert{
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private DetailTypeRepository detailTypeRepository;
    private Detail detail;
    private DetailType detailType;
    @Before
    public void setDetailData(){
        detailType=new DetailType();
        detailType.setName("Processor");
        detailType.setProduceTime(10);
        detail=new Detail();
        detail.setName("Core I3");
        detail.setPower(3);
        detail.setQuality(5);
        detail.setPrice(200);
        detail.setDetailType(detailType);
    }
    @Test
    public void testSaveDetail(){
        detail=detailRepository.save(detail);
        detail=detailRepository.findOne(detail.getId());
        detailType=detailTypeRepository.findOne(detail.getDetailType().getId());
        assertEquals(detail.getName(),"Core I3");
        assertEquals(detail.getDetailType().getName(),"Processor");
    }
    @After
    public void deleteDetailData(){
        detailRepository.delete(detail);
        detailTypeRepository.delete(detailType);
        assertEquals(detailRepository.findOne(detail.getId()),null);
    }

}
