package com.ecom.zahranStore.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.zahranStore.model.Product;
import com.ecom.zahranStore.service.ProductService;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {
    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable UUID categoryId) {
        List<Product> categoryProducts = productService.getProductByCategoryId(categoryId);
        return new ResponseEntity<>(categoryProducts, HttpStatus.OK);
    }
}
