package com.codesupreme.kenanustaapi.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Long id;

    private String name;

    private Date createdAt;
    private Date updatedAt;
}
