package com.repository;

import com.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by andron94 on 18.04.15.
 */
public interface OrderRepository extends PagingAndSortingRepository<Order,Integer> {
    public List<Order> findByUser_Email(String email);
    public Order findOneByIdAndUser_Email(int orderId,String email);
}
