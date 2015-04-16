package com.repository;

import com.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 15.03.15.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    public UserRole findOneByRole(String role);
}
