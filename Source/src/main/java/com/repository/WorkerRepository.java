package com.repository;

import com.model.Detail;
import com.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 17.03.15.
 */
public interface WorkerRepository  extends JpaRepository<Worker,Integer> {
}
