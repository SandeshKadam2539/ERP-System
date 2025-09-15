package com.erp.controller;

import com.erp.dto.GRNRequestDTO;
import com.erp.dto.GRNResponseDTO;
import com.erp.service.GRNService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grns")
public class GRNController {

    private final GRNService grnService;

    public GRNController(GRNService grnService) {
        this.grnService = grnService;
    }

    @PostMapping
    public ResponseEntity<GRNResponseDTO> createGRN(@RequestBody GRNRequestDTO dto) {
        GRNResponseDTO createdGrn = grnService.createGRN(dto);
        return ResponseEntity.ok(createdGrn);
    }

    @GetMapping
    public ResponseEntity<List<GRNResponseDTO>> getAll() {
        List<GRNResponseDTO> grns = grnService.getAllGRNs();
        return ResponseEntity.ok(grns);
    }
}
