package com.ecom.zahranStore.config;

import com.ecom.zahranStore.model.Category;
import com.ecom.zahranStore.model.Product;
import com.ecom.zahranStore.repository.CategoryRepository;
import com.ecom.zahranStore.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private static final String PLACEHOLDER_IMAGE_URL = "https://picsum.photos/seed/zahranstore/500/500";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DataSeeder(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.deleteAll();
        categoryRepository.deleteAll();

        // --- categories ---
        Category electronics = createCategory("Electronics");
        Category clothing = createCategory("Clothing");
        Category homeAndKitchen = createCategory("Home & Kitchen");

        categoryRepository.saveAll(List.of(electronics, clothing, homeAndKitchen));

        // --- products ---
        List<Product> products = List.of(
                createProduct("Wireless Headphones", "Over-ear Bluetooth headphones with noise cancellation",
                        PLACEHOLDER_IMAGE_URL, 79.99, electronics),
                createProduct("Smartphone Stand", "Adjustable aluminum stand for phones and tablets",
                        PLACEHOLDER_IMAGE_URL, 15.99, electronics),
                createProduct("4K Monitor", "27-inch UHD monitor with HDR support",
                        PLACEHOLDER_IMAGE_URL, 249.99, electronics),

                createProduct("Men's T-Shirt", "100% cotton crew neck t-shirt",
                        PLACEHOLDER_IMAGE_URL, 12.99, clothing),
                createProduct("Women's Denim Jacket", "Classic fit denim jacket",
                        PLACEHOLDER_IMAGE_URL, 45.99, clothing),
                createProduct("Running Shoes", "Lightweight breathable running shoes",
                        PLACEHOLDER_IMAGE_URL, 59.99, clothing),

                createProduct("Non-Stick Frying Pan", "10-inch non-stick frying pan with ergonomic handle",
                        PLACEHOLDER_IMAGE_URL, 22.99, homeAndKitchen),
                createProduct("Electric Kettle", "1.7L stainless steel electric kettle",
                        PLACEHOLDER_IMAGE_URL, 34.99, homeAndKitchen),
                createProduct("Ceramic Dinner Set", "16-piece ceramic dinnerware set",
                        PLACEHOLDER_IMAGE_URL, 89.99, homeAndKitchen)
        );

        productRepository.saveAll(products);
    }

    private Category createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }

    private Product createProduct(String name, String description, String imageUrl, Double price, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setPrice(price);
        product.setCategory(category);
        return product;
    }
}