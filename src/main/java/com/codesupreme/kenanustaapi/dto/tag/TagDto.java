package com.codesupreme.kenanustaapi.dto.tag;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "label boş ola bilməz")
    @Size(max = 255, message = "label maksimum 255 simvol ola bilər")
    private String label;

    @NotBlank(message = "slug boş ola bilməz")
    @Size(max = 255, message = "slug maksimum 255 simvol ola bilər")
    private String slug;
}
