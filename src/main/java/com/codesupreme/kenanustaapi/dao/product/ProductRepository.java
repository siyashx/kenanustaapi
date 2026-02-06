package com.codesupreme.kenanustaapi.dao.product;

import com.codesupreme.kenanustaapi.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
