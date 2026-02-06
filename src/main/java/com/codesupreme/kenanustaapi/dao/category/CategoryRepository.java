package com.codesupreme.kenanustaapi.dao.category;

import com.codesupreme.kenanustaapi.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
