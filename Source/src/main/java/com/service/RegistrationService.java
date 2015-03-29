package com.service;

import com.model.User;
import com.model.UserRole;
import com.repository.UserRepository;
import com.repository.UserRoleRepository;
import com.util.MailUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by root on 15.03.15.
 */
@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserRoleRepository roleRepository;
    @Transactional
    public User registerNewUserAccount(User user) throws RuntimeException {
        if (emailExist(user.getEmail())) {
            throw new RuntimeException("There is an account with that email adress: " + user.getEmail());
        }
            user.setEnabled(false);
            UserRole role=roleRepository.findOneByRole("ROLE_USER");
            Set<UserRole> roles=new HashSet<UserRole>();
            roles.add(role);

            String key= MailUtil.hash(user.getEmail() + "." + user.getPassword());
            user.setKey(key);
            userRepository.save(user);
        return user;
    }

    private boolean emailExist(String email) {
        User user = userRepository.findOneByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
    @Transactional
    public boolean activateUser(String key){
        User user = userRepository.findOneByKey(key);
        if (user != null) {
            user.setKey(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Transactional
    public void deleteUser(int userid){
        userRepository.delete(userid);
    }
    public boolean sendActivationLetter(String email,String key){
        return    MailUtil.sendMail(email,
                "Success Registration",
                "Please follow for reference to activation\n <a href=\"http://localhost:8080/activation?key="
                        +key+"\">Activation Link</a>");
    }

}
