package com.erp.repository;


import com.erp.entity.SalesOrderItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SalesOrderItemRepository extends JpaRepository<SalesOrderItem, Long> {

}