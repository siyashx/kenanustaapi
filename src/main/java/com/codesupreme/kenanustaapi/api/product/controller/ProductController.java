package com.codesupreme.kenanustaapi.api.product.controller;

import com.codesupreme.kenanustaapi.dto.product.ProductDto;
import com.codesupreme.kenanustaapi.service.impl.product.ProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v666/product")
public class ProductController {

    private final ProductServiceImpl service;
    public ProductController(ProductServiceImpl service) {this.service = service;}

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> all = service.getAllProduct();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Long id) {
        ProductDto det = service.getProductById(id);
        if (det != null) {
            return ResponseEntity.ok(det);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto dto) {
        ProductDto created = service.saveProduct(dto);
        return ResponseEntity.ok(created);
    }

    // Update
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(
            @PathVariable("productId") Long id,
            @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = service.updateProduct(id, productDto);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long id) {
        Boolean deleted = service.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.ok("Admin product deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
