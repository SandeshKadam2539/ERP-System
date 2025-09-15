package com.erp.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PurchaseSummaryDTO {
    private Long totalPOs;          // all POs in range
    private Long orderedPOs;        // status = ORDERED
    private Long receivedPOs;       // status = RECEIVED
}