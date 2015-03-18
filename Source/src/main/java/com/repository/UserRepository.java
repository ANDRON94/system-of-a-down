package com.repository;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 13.03.15.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findOneByEmail(String email);
    public User findOneByKey(String key);
}
