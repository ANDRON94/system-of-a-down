package com.dao;

import com.repository.ContractRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by andron94 on 29.04.15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class ContractRepositoryTest {
    @Autowired
    ContractRepository contractRepository;

    @Test
    public void deleteContractsAfterDateTest(){
        contractRepository.deleteContractsAfterDateAnd(new Date());
    }
}
