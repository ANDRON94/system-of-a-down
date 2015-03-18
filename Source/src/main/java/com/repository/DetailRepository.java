package com.repository;

import com.model.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by root on 10.03.15.
 */
//Repository of Record (extends CrudOperations)
public interface DetailRepository extends JpaRepository<Detail,Integer> {
}
