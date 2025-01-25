package com.test.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.admin.entity.IngredientProduct;

public interface IngredientProductRepository extends JpaRepository<IngredientProduct, Long>{

}
