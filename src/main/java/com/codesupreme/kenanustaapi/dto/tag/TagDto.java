package com.codesupreme.kenanustaapi.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {

    private Long id;
    private String label;
    private String slug;
}

