package com.erp.service;

import com.erp.dto.SalesOrderRequestDTO;
import com.erp.entity.SalesOrder;

import java.util.List;

public interface SalesOrderService {
    SalesOrder createOrder(SalesOrderRequestDTO request);
    List<SalesOrder> getAllOrders();
}
