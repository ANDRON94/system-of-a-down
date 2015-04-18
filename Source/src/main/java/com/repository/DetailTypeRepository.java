package com.repository;

import com.model.DetailType;
import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 12.04.15.
 */
public interface DetailTypeRepository extends JpaRepository<DetailType,Integer> {
}
