package com.codesupreme.kenanustaapi.service.impl.category;

import com.codesupreme.kenanustaapi.dao.category.CategoryRepository;
import com.codesupreme.kenanustaapi.dto.category.CategoryDto;
import com.codesupreme.kenanustaapi.model.category.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    //ALL
    public List<CategoryDto> getAllCategory() {
        List<Category> list = categoryRepository.findAll();
        return list.stream()
                .map(det -> modelMapper.map(det, CategoryDto.class))
                .toList();
    }

    //ById
    public CategoryDto getCategoryById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.map(det -> modelMapper.map(det, CategoryDto.class)).orElse(null);
    }

    //Save
    public CategoryDto saveCategory(CategoryDto dto) {
        Category det = modelMapper.map(dto, Category.class);
        det = categoryRepository.save(det);
        return modelMapper.map(det, CategoryDto.class);
    }

    //Update
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if (optional.isPresent()) {
            Category category = optional.get();


            if (categoryDto.getName() != null) {
                category.setName(categoryDto.getName());
            }

            if (categoryDto.getCreatedAt() != null) {
                categoryDto.setCreatedAt(categoryDto.getCreatedAt());
            }

            if (categoryDto.getUpdatedAt() != null) {
                categoryDto.setUpdatedAt(categoryDto.getUpdatedAt());
            }

            category = categoryRepository.save(category);

            return modelMapper.map(category, CategoryDto.class);
        }
        return null;
    }

    //Delete
    public Boolean deleteCategory(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Category det = optional.get();
            categoryRepository.delete(det);
            return true;
        }
        return false;
    }


}


