package com.erp.repository;

import com.erp.entity.SalesOrder;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    
}