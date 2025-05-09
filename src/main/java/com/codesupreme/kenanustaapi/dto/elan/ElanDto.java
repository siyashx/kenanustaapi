package com.codesupreme.kenanustaapi.dto.elan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElanDto {

    private Long id;

    private String title;
    private String description;
    private String category;
    private String city;
    private String imagePath;
    private String slug;

    private Date createdAt;
    private Date updatedAt;
}
