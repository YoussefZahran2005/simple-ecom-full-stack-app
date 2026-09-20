package com.ecom.zahranStore.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.zahranStore.model.Category;
import com.ecom.zahranStore.model.Product;

@Repository 
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> FindByCategoryId(UUID categoryId);

}
