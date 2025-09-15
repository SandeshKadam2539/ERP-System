package com.erp.service;

import com.erp.dto.*;
import com.erp.entity.*;
import com.erp.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository orderRepo;
    private final SupplierRepository supplierRepo;
    private final ProductRepository productRepo;

    public PurchaseOrderService(PurchaseOrderRepository orderRepo,
                                SupplierRepository supplierRepo,
                                ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
    }

    // Purchase Order तयार करणे
    public PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO dto) {
        Supplier supplier = supplierRepo.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        PurchaseOrder order = new PurchaseOrder();
        order.setSupplier(supplier); // ✅ Supplier set
        order.setOrderDate(dto.getOrderDate() != null ? dto.getOrderDate() : LocalDate.now());
        order.setExpectedDeliveryDate(dto.getExpectedDeliveryDate());
        order.setStatus(dto.getStatus() != null ? dto.getStatus().name() : PurchaseOrderStatus.ORDERED.name());

        BigDecimal total = BigDecimal.ZERO;
        List<PurchaseOrderItem> itemList = new ArrayList<>();

        for (PurchaseOrderItemDTO itemDto : dto.getItems()) {
            Product product = productRepo.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            PurchaseOrderItem item = new PurchaseOrderItem();
            item.setProduct(product);
            item.setQuantity(itemDto.getQuantity());
            item.setUnitPrice(BigDecimal.valueOf(product.getUnitPrice()));
            item.setPurchaseOrder(order);

            itemList.add(item);

            total = total.add(item.getUnitPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())));
        }

        order.setItems(itemList);
        order.setTotalAmount(total);

        PurchaseOrder savedOrder = orderRepo.save(order);
        return mapToDTO(savedOrder);
    }

    // सर्व Orders मिळवणे
    public List<PurchaseOrderResponseDTO> getAllOrders() {
        return orderRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Order Status Update करणे
    public PurchaseOrderResponseDTO updateStatus(Long id, PurchaseOrderStatus status) {
        PurchaseOrder order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status.name());
        PurchaseOrder updatedOrder = orderRepo.save(order);
        return mapToDTO(updatedOrder);
    }

    // Entity → DTO Mapping
    private PurchaseOrderResponseDTO mapToDTO(PurchaseOrder order) {
        PurchaseOrderResponseDTO dto = new PurchaseOrderResponseDTO();
        dto.setId(order.getId());
        dto.setSupplierName(order.getSupplier().getName());
        dto.setOrderDate(order.getOrderDate());
        dto.setExpectedDeliveryDate(order.getExpectedDeliveryDate());
        dto.setStatus(order.getStatus());
        dto.setTotalAmount(order.getTotalAmount());

        List<PurchaseOrderItemDetailDTO> items = order.getItems().stream().map(i -> {
            PurchaseOrderItemDetailDTO d = new PurchaseOrderItemDetailDTO();
            d.setProductName(i.getProduct().getProductName());
            d.setQuantity(i.getQuantity());
            d.setUnitPrice(i.getUnitPrice());
            return d;
        }).collect(Collectors.toList());

        dto.setItems(items);
        return dto;
    }
}
