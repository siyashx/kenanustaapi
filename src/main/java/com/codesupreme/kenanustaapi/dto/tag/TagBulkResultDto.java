package com.codesupreme.kenanustaapi.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagBulkResultDto {
    @Builder.Default
    private List<TagDto> created = new ArrayList<>();

    @Builder.Default
    private List<TagDto> skipped = new ArrayList<>();

    @Builder.Default
    private List<String> failed = new ArrayList<>();
}
