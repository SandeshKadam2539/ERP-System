package com.erp.repository;

import com.erp.entity.PurchaseOrder; // create if not present
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("select count(po) from PurchaseOrder po where po.orderDate between :from and :to")
    Long countAllInRange(@Param("from") Date from, @Param("to") Date to);

    @Query("select count(po) from PurchaseOrder po where lower(po.status) = lower(:status) and po.orderDate between :from and :to")
    Long countByStatusInRange(@Param("status") String status, @Param("from") Date from, @Param("to") Date to);
}