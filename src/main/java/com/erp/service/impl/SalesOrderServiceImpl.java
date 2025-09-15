package com.erp.service.impl;

import com.erp.dto.SalesOrderRequestDTO;
import com.erp.entity.*;
import com.erp.repository.*;
import com.erp.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public SalesOrder createOrder(SalesOrderRequestDTO request) {
        SalesOrder order = new SalesOrder();
        order.setCustomerName(request.getCustomerName());
        order.setOrderDate(new Date());

        for (SalesOrderRequestDTO.OrderItemDTO itemDTO : request.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + itemDTO.getProductId()));

            SalesOrderItem item = new SalesOrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            order.addItem(item);
        }

        return salesOrderRepository.save(order);
    }

    @Override
    public List<SalesOrder> getAllOrders() {
        return salesOrderRepository.findAll();
    }
}
