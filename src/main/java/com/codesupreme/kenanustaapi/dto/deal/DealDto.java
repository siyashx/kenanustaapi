package com.codesupreme.kenanustaapi.dto.deal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DealDto {

        private Long id;

        private Long productId;

        private Integer newPrice;

        private Date createdAt;
        private Date updatedAt;

}
