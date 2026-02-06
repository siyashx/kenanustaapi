package com.codesupreme.kenanustaapi.service.impl.product;

import com.codesupreme.kenanustaapi.dao.product.ProductRepository;
import com.codesupreme.kenanustaapi.dto.product.ProductDto;
import com.codesupreme.kenanustaapi.model.product.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    //ALL
    public List<ProductDto> getAllProduct() {
        List<Product> list = productRepository.findAll();
        return list.stream()
                .map(det -> modelMapper.map(det, ProductDto.class))
                .toList();
    }

    //ById
    public ProductDto getProductById(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        return optional.map(det -> modelMapper.map(det, ProductDto.class)).orElse(null);
    }

    //Save
    public ProductDto saveProduct(ProductDto dto) {
        Product det = modelMapper.map(dto, Product.class);
        det = productRepository.save(det);
        return modelMapper.map(det, ProductDto.class);
    }

    //Update
    public ProductDto updateProduct(Long productId, ProductDto productDto) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isPresent()) {
            Product product = optional.get();

            if (productDto.getCategoryId() != null) {
                product.setCategoryId(productDto.getCategoryId());
            }

            if (productDto.getTitle() != null) {
                product.setTitle(productDto.getTitle());
            }

            if (productDto.getDescription() != null) {
                product.setDescription(productDto.getDescription());
            }

            if (productDto.getImageUrl() != null) {
                product.setImageUrl(productDto.getImageUrl());
            }

            if (productDto.getPrice() != null) {
                product.setPrice(productDto.getPrice());
            }

            if (productDto.getCreatedAt() != null) {
                productDto.setCreatedAt(productDto.getCreatedAt());
            }

            if (productDto.getUpdatedAt() != null) {
                productDto.setUpdatedAt(productDto.getUpdatedAt());
            }

            product = productRepository.save(product);

            return modelMapper.map(product, ProductDto.class);
        }
        return null;
    }

    //Delete
    public Boolean deleteProduct(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product det = optional.get();
            productRepository.delete(det);
            return true;
        }
        return false;
    }


}


