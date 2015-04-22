package com.repository;

import com.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by andron94 on 18.04.15.
 */
public interface OrderRepository extends PagingAndSortingRepository<Order,Integer> {
    public List<Order> findByUser_Email(String email);
    public Order findOneByIdAndUser_Email(int orderId,String email);
    public List<Order> findByStatus_Name(String statusName);

    /*@Query("select distinct o from Order as o inner join" +
            " o.status as s inner join" +
            " o.contractList as c where s.name=:pending or (s.name=:processing and c.start_date > :datepick) ")*/
    @Query("select distinct o from Order as o inner join" +
            " o.status as s inner join" +
            " o.contractList as c where s.name=:pending")
    public List<Order> findAllOrdersForPlane(@Param("pending") String pending);
}
