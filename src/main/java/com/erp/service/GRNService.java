package com.erp.service;

import com.erp.dto.GRNItemDTO;
import com.erp.dto.GRNRequestDTO;
import com.erp.dto.GRNResponseDTO;
import com.erp.entity.GRN;
import com.erp.entity.GRNItem;
import com.erp.entity.Product;
import com.erp.entity.PurchaseOrder;
import com.erp.repository.GRNRepository;
import com.erp.repository.ProductRepository;
import com.erp.repository.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GRNService {

    private final GRNRepository grnRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public GRNService(
            GRNRepository grnRepository,
            ProductRepository productRepository,
            PurchaseOrderRepository purchaseOrderRepository
    ) {
        this.grnRepository = grnRepository;
        this.productRepository = productRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Transactional
    public GRNResponseDTO createGRN(GRNRequestDTO dto) {
        GRN grn = new GRN();
        grn.setReceivedDate(dto.getReceivedDate() != null ? dto.getReceivedDate() : LocalDate.now());

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(dto.getPurchaseOrderId())
                .orElseThrow(() -> new RuntimeException("Purchase Order not found"));
        grn.setPurchaseOrder(purchaseOrder);

        List<GRNItem> grnItems = new ArrayList<>();

        for (GRNItemDTO itemDto : dto.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Update product stock
            product.setCurrentStock(product.getCurrentStock() + itemDto.getReceivedQuantity());
            productRepository.save(product);

            GRNItem item = new GRNItem();
            item.setProduct(product);
            item.setReceivedQuantity(itemDto.getReceivedQuantity());
            item.setGrn(grn);
            grnItems.add(item);
        }

        grn.setItems(grnItems);
        GRN saved = grnRepository.save(grn);

        return convertToDto(saved);
    }

    public List<GRNResponseDTO> getAllGRNs() {
        List<GRN> grns = grnRepository.findAll();
        return grns.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private GRNResponseDTO convertToDto(GRN grn) {
        GRNResponseDTO dto = new GRNResponseDTO();
        dto.setId(grn.getId());
        dto.setReceivedDate(grn.getReceivedDate());
        dto.setPurchaseOrderId(grn.getPurchaseOrder().getId());

        List<GRNItemDTO> itemDTOs = grn.getItems().stream().map(item -> {
            GRNItemDTO itemDto = new GRNItemDTO();
            itemDto.setProductId(item.getProduct().getId());
            itemDto.setProductName(item.getProduct().getProductName()); // ✅

            itemDto.setReceivedQuantity(item.getReceivedQuantity());
            return itemDto;
        }).collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }
}
