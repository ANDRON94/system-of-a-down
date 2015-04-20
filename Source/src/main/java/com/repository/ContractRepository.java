package com.repository;

import com.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 20.04.15.
 */
public interface ContractRepository extends JpaRepository<Contract,Integer> {
}
