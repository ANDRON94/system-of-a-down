package com.repository;

import com.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andron94 on 18.04.15.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public Page<Order> findAllOrderByIdDesc(Pageable pageable);
}
