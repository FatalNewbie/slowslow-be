package com.elice.slowslow.product.service;

import com.elice.slowslow.brand.Brand;
import com.elice.slowslow.brand.repository.BrandRepository;
import com.elice.slowslow.category.Category;
import com.elice.slowslow.category.repository.CategoryRepository;
import com.elice.slowslow.product.Product;
import com.elice.slowslow.product.dto.ProductDto;
import com.elice.slowslow.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;


    public void createProduct(ProductDto productDto) {
        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new IllegalStateException("Brand does not exist"));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category does not exist"));

        Product product = Product.fromDto(productDto, brand, category);
        productRepository.save(product);
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return (product != null) ? product.toDto() : null;
    }


    @Transactional
    public void updatePost(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElse(null);

        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new IllegalStateException("Brand does not exist"));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category does not exist"));

        if (product != null) {
            product.setName(productDto.getName());
            product.setPrice(product.getPrice());
            product.setDescription(product.getDescription());
            product.setImageLink(product.getImageLink());
            product.setBrand(brand);
            product.setCategory(category);
            productRepository.save(product);

        }
    }


    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }



}