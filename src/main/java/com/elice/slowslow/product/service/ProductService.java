package com.elice.slowslow.product.service;

import com.elice.slowslow.brand.Brand;
import com.elice.slowslow.brand.repository.BrandRepository;
import com.elice.slowslow.category.Category;
import com.elice.slowslow.category.repository.CategoryRepository;
import com.elice.slowslow.product.Product;
import com.elice.slowslow.product.dto.ProductDto;
import com.elice.slowslow.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;


    public ProductDto createProduct(ProductDto productDto) {
        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new IllegalStateException("Brand does not exist"));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category does not exist"));

        Product product = Product.fromDto(productDto, brand, category);
        Product saveProduct = productRepository.save(product);
        return saveProduct.toDto();
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("product does not exist"));;
        return product.toDto();
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(Product::toDto).collect(Collectors.toList());
    }


    @Transactional
    public ProductDto updatePost(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElse(null);

        Brand brand = brandRepository.findById(productDto.getBrandId())
                .orElseThrow(() -> new IllegalStateException("Brand does not exist"));
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category does not exist"));


            product.setName(productDto.getName());
            product.setPrice(product.getPrice());
            product.setDescription(product.getDescription());
            product.setImageLink(product.getImageLink());
            product.setBrand(brand);
            product.setCategory(category);
            Product updateProduct = productRepository.save(product);

        return updateProduct.toDto();
    }


    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("product does not exist"));
        productRepository.delete(product);
    }



}