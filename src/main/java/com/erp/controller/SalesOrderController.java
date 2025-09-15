package com.erp.controller;

import com.erp.dto.SalesOrderRequestDTO;
import com.erp.entity.SalesOrder;
import com.erp.service.SalesOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {

    @Autowired
    private SalesOrderService salesOrderService;

    @PostMapping
    public SalesOrder createOrder(@RequestBody SalesOrderRequestDTO request) {
        return salesOrderService.createOrder(request);
    }

    @GetMapping
    public List<SalesOrder> getAllOrders() {
        return salesOrderService.getAllOrders();
    }
}
