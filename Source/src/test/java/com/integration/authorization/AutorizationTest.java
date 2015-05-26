package com.integration.authorization;


import com.model.User;
import com.model.UserRole;
import com.repository.UserRepository;
import com.repository.UserRoleRepository;
import org.junit.After;
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
public class AutorizationTest extends Assert {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    private String email;
    private String password;
    private User user;
    @Before
    public void setEmailAndPassword(){
        password="test";
        email="test@gmail.com";
        user = new User();
        user.setEmail(email);
        user.setEnabled(true);
        user.setFirstName("Vasya");
        user.setLastName("Pupkin");
        user.setPassword(password);
        user.setUserRole(userRoleRepository.findOneByRole("ROLE_USER"));
        user.setKey(null);
        if(userRepository.findOneByEmail(user.getEmail())==null){
            userRepository.save(user);

        }
    }
    @Test
    public void testSuccessUserResearch(){

        user=userRepository.findOneByEmail(email);
        assertEquals(user.getPassword(),"test");
        assertEquals(user.getEmail(),"test@gmail.com");
    }
    @After
    public void afterSuccessAutorization(){
        userRepository.delete(user.getIduser());
    }
}
