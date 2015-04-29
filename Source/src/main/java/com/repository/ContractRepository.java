package com.repository;

import com.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 20.04.15.
 */
public interface ContractRepository extends JpaRepository<Contract,Integer> {
    @Query("select  c from Contract as c inner join" +
            " c.order as o inner join o.status as s inner" +
            " join c.detail as d where s.name=:procesing and c.start_date > :datePick")
    public List<Contract> findByStartAfterAndOrderStatusName(@Param("procesing")String statusName,@Param("datePick")Date date);

    @Modifying
    @Transactional
    @Query("delete from Contract c where c.start_date >= :time")
    public void deleteContractsAfterDateAnd(@Param("time")Date name);

    @Query("select c from Contract c where c.start_date > :datePick and c.order.id=:idOrder")
    public List<Contract> getContractsAfterDateByIdOrder(@Param("datePick") Date date,@Param("idOrder") int idOrder);
}
