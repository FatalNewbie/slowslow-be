package com.elice.slowslow.brand.dto;

import com.elice.slowslow.brand.Brand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandPutDto {
    private Long id;

    private String brandName;

    public Brand toEntity(){
        Brand brand = new Brand();
        brand.setId(id);
        brand.setBrandName(brandName);
        return brand;
    }

    public BrandPutDto(Brand brand) {
        this.id = brand.getId();
        this.brandName = brand.getBrandName();
    }
}
