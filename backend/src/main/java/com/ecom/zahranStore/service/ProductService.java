package com.ecom.zahranStore.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ecom.zahranStore.model.Product;
import com.ecom.zahranStore.repository.ProductRepository;

@Service 
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductByCategoryId(UUID categoryId) {
        return productRepository.FindByCategoryId(categoryId);
    }
}
