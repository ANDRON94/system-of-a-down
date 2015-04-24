package com.repository;

import com.model.DetailType;
import com.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 22.04.15.
 */
public interface StatusRepository  extends JpaRepository<Status,Integer> {
}
