package com.codesupreme.kenanustaapi.api.elan.controller;


import com.codesupreme.kenanustaapi.dto.elan.ElanDto;
import com.codesupreme.kenanustaapi.service.impl.elan.ElanServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v666/elan")
public class ElanController {
    
    private final ElanServiceImpl service;
    public ElanController(ElanServiceImpl service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<ElanDto>> getAllElan() {
        List<ElanDto> all = service.getAllElan();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{elanId}")
    public ResponseEntity<ElanDto> getElanById(@PathVariable("elanId") Long id) {
        ElanDto det = service.getElanById(id);
        if (det != null) {
            return ResponseEntity.ok(det);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ElanDto> saveElan(@RequestBody ElanDto dto) {
        ElanDto created = service.saveElan(dto);
        return ResponseEntity.ok(created);
    }

    // Update
    @PutMapping("/{elanId}")
    public ResponseEntity<?> updateElan(
            @PathVariable("elanId") Long id,
            @RequestBody ElanDto elanDto) {
        ElanDto updatedElan = service.updateElan(id, elanDto);
        if (updatedElan != null) {
            return ResponseEntity.ok(updatedElan);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{elanId}")
    public ResponseEntity<String> deleteElan(@PathVariable("elanId") Long id) {
        Boolean deleted = service.deleteElan(id);
        if (deleted) {
            return ResponseEntity.ok("Admin elan deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
