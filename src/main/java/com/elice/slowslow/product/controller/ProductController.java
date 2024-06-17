package com.elice.slowslow.product.controller;

import com.elice.slowslow.product.Product;
import com.elice.slowslow.product.dto.ProductDto;
import com.elice.slowslow.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ProductController {


    private ProductService productService;


    @PostMapping("/product/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {

        return productService.createProduct(productDto);
    }

    @PostMapping("/product/update/{productId}")
    public ProductDto updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {

        return  productService.updatePost(productId, productDto); // 수정한 페이지로 이동하게 할 예정.
    }



    @GetMapping("product/{productId}")
    public ProductDto getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @DeleteMapping("/product/delete/{productId}")
    public void deleteProduct(@PathVariable Long productId) {

        productService.deleteProduct(productId);

    }


}
