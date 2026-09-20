package com.ecom.zahranStore.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.zahranStore.model.Category;
import com.ecom.zahranStore.model.Product;

@Repository 
public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
