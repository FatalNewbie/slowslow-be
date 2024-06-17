package com.elice.slowslow.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private Long price;
    private String description;
    private String imageLink;
    private LocalDateTime createdDate;
    private Long brandId;
    private Long categoryId;

    @Builder
    public ProductDto(Long id, String name, Long price, String description,
                      String imageLink, LocalDateTime createdDate, Long brandId, Long categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageLink = imageLink;
        this.createdDate = createdDate;
        this.brandId = brandId;
        this.categoryId = categoryId;

    }


}
