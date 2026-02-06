package com.codesupreme.kenanustaapi.api.deals.controller;

import com.codesupreme.kenanustaapi.dto.deal.DealDto;
import com.codesupreme.kenanustaapi.service.impl.deal.DealServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v666/deal")
public class DealsController {

    private final DealServiceImpl service;
    public DealsController(DealServiceImpl service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<DealDto>> getAllDeal() {
        List<DealDto> all = service.getAllDeal();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{dealId}")
    public ResponseEntity<DealDto> getDealById(@PathVariable("dealId") Long id) {
        DealDto det = service.getDealById(id);
        if (det != null) {
            return ResponseEntity.ok(det);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DealDto> saveDeal(@RequestBody DealDto dto) {
        DealDto created = service.saveDeal(dto);
        return ResponseEntity.ok(created);
    }

    // Update
    @PutMapping("/{dealId}")
    public ResponseEntity<?> updateDeal(
            @PathVariable("dealId") Long id,
            @RequestBody DealDto dealDto) {
        DealDto updatedDeal = service.updateDeal(id, dealDto);
        if (updatedDeal != null) {
            return ResponseEntity.ok(updatedDeal);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{dealId}")
    public ResponseEntity<String> deleteDeal(@PathVariable("dealId") Long id) {
        Boolean deleted = service.deleteDeal(id);
        if (deleted) {
            return ResponseEntity.ok("Admin deal deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
