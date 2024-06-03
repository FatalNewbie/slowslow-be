package com.elice.slowslow.brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BrandResponseDto {
    private Long id;

    private String brandName;

    public Brand toEntity() {
        Brand brand = new Brand();
        brand.setId(id);
        brand.setBrandName(brandName);

        return brand;
    }

    public BrandResponseDto(Brand brand) {
        this.id = brand.getId();
        this.brandName = brand.getBrandName();
    }
}
