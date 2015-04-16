package com.dao;


import com.model.User;
import com.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


/**
 * Created by root on 24.03.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/database.xml"})
public class getAutorizeUserTest extends Assert {

    @Autowired
    private UserRepository userRepository;

    private String email;
    private String password;
    @Before
    public void setEmailAndPassword(){
        password="2222";
        email="kmetsmi@gmail.com";
    }
    @Test
    public void testSuccessUserResearch(){
        User user=userRepository.findOneByEmail(email);
        assertEquals(user.getPassword(),password);
    }
}
