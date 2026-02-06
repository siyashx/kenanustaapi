package com.codesupreme.kenanustaapi.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private Integer price;

    private Date createdAt;
    private Date updatedAt;
}
