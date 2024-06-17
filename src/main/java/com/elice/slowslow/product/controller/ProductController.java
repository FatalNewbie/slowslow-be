package com.elice.slowslow.product.controller;

import com.elice.slowslow.product.dto.ProductDto;
import com.elice.slowslow.product.service.ProductService;
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
    public String createProduct(@ModelAttribute ProductDto productDto) {

        productService.createProduct(productDto);

        return "managerPage"; // 관리자 페이지로 이동
    }

    @PostMapping("/product/edit/{productId}")
    public String editProduct(@PathVariable Long productId, @ModelAttribute ProductDto productDto) {
        productService.updatePost(productId, productDto);
        return "productDetail"; // 수정한 페이지로 이동하게 할 예정.
    }

    @DeleteMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        // 상품 상세 페이지에서 삭제하게끔?
        productService.deleteProduct(productId);
        return "productPage"; // 상세 페이지 들어가기 전 페이지로
    }


    @GetMapping("product/{productId}")
    public String showProductDetail(@PathVariable Long productId, Model model) {

        ProductDto product = productService.getProductById(productId);

        model.addAttribute("products", product);

        return "productDetail";
    }



}
